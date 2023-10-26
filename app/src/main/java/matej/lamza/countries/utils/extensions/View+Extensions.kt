package matej.lamza.countries.utils.extensions

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ShimmerFrameLayout.stop() {
    gone()
    stopShimmer()
}

fun ShimmerFrameLayout.start() {
    visible()
    startShimmer()
}

