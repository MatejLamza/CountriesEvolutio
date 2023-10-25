package matej.lamza.countries.ui.details

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ItemCountryBorderBinding

class CountryBordersAdapter : BindingListAdapter<String, CountryBordersAdapter.CountryBorderViewHolder>(diffUtil) {

    var onCountryBorderClicked: ((countryCode: String) -> Unit)? = null

    override fun onBindViewHolder(holder: CountryBorderViewHolder, position: Int) {
        holder.bindBorder(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryBorderViewHolder {
        return parent.binding<ItemCountryBorderBinding>(R.layout.item_country_border).let(::CountryBorderViewHolder)
    }

    inner class CountryBorderViewHolder constructor(
        private val binding: ItemCountryBorderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onCountryBorderClicked?.invoke(getItem(layoutPosition))
            }
        }

        fun bindBorder(border: String) {
            binding.border = border
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }

    }

}
