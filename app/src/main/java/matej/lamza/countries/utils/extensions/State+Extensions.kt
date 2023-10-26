package matej.lamza.countries.utils.extensions

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import matej.lamza.countries.common.state.State

fun State.handleUIState(
    parent: View,
    shimmerContainer: ShimmerFrameLayout,
    onLoading: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
    onError: ((throwable: Throwable?) -> Unit)? = null
) {
    when (this) {
        is State.Loading -> {
            shimmerContainer.start()
            onLoading?.invoke()
        }

        is State.Done -> {
            shimmerContainer.stop()
            onDone?.invoke()
        }

        is State.Error -> {
            Snackbar.make(parent, "Error occurred: ${throwable?.message}", Snackbar.LENGTH_LONG).show()
            onError?.invoke(this.throwable)
        }
    }
}
