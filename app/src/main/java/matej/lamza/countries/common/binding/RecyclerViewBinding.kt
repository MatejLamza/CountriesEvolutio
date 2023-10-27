package matej.lamza.countries.common.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.whatif.whatIfNotNullAs
import matej.lamza.countries.R

object RecyclerViewBinding {

    /**
     * submitList is property that we will be using inside our layout item XML to assign list of items that will be
     * displayed in recycler view.
     * This will make sure to trigger diff utils that our recycler view adapter is using to refresh contents of the
     * [RecyclerView]
     */
    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun bindImage(view: AppCompatImageView, url: String?) {
        val context = view.context
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.flag)
            .into(view)
    }
}
