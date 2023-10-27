package matej.lamza.countries.common.binding

import android.view.View
import androidx.databinding.BindingAdapter
import matej.lamza.core_model.Country

object ViewBinding {

    @JvmStatic
    @BindingAdapter("shouldBeGone")
    fun setViewVisibility(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("setVisibilityWithList")
    fun setViewVisibilityForList(view: View, list: List<Country>) {
        view.visibility = if (list.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
