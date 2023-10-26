package matej.lamza.countries.utils.extensions

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import matej.lamza.countries.common.state.State
import matej.lamza.countries.utils.ErrorMapper

fun State.handleUIState(
    parent: View,
    shimmerContainer: ShimmerFrameLayout? = null,
    onLoading: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
    onError: ((throwable: Throwable?) -> Unit)? = null
) {
    when (this) {
        is State.Loading -> {
            shimmerContainer?.start()
            onLoading?.invoke()
        }

        is State.Done -> {
            shimmerContainer?.stop()
            onDone?.invoke()
        }

        is State.Error -> {
            val errorMessage = throwable.let { ErrorMapper.getMessage(it, parent.context) }
            Snackbar.make(parent, "Error occurred: $errorMessage", Snackbar.LENGTH_LONG).show()
            onError?.invoke(this.throwable)
        }
    }
}
