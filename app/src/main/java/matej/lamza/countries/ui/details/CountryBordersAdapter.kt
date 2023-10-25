package matej.lamza.countries.ui.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingRecyclerViewAdapter
import com.skydoves.bindables.binding
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ItemCountryBorderBinding

class CountryBordersAdapter : BindingRecyclerViewAdapter<CountryBordersAdapter.CountryBorderViewHolder>() {

    private val items = mutableListOf<String>()

    override fun getItemCount(): Int = items.count()

    fun addBorderList(list: List<String>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryBorderViewHolder {
        return parent.binding<ItemCountryBorderBinding>(R.layout.item_country_border).let(::CountryBorderViewHolder)
    }

    override fun onBindViewHolder(holder: CountryBorderViewHolder, position: Int) {
        holder.bindBorder(items[position])
    }

    inner class CountryBorderViewHolder constructor(
        private val binding: ItemCountryBorderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                //open new activity with different country
            }
        }

        fun bindBorder(border: String) {
            binding.border = border
            binding.executePendingBindings()
        }
    }

}
