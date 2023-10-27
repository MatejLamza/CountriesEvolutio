package matej.lamza.countries.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import matej.lamza.core_model.Country
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ItemCountryBinding
import matej.lamza.countries.ui.details.DetailsActivity
import matej.lamza.countries.utils.CountrySortOptions

class CountryAdapter : BindingListAdapter<Country, CountryAdapter.CountryViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem == newItem
        }
    }

    private val originalList by lazy { currentList }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindCountry(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return parent.binding<ItemCountryBinding>(R.layout.item_country).let(::CountryViewHolder)
    }

    fun sortCountries(countrySortOptions: CountrySortOptions) {
        val sortedList = when (countrySortOptions) {
            CountrySortOptions.ALPHABETICALLY -> originalList.sortedBy { it.name }
            CountrySortOptions.AREA -> originalList.sortedBy { it.area }
            CountrySortOptions.POPULATION -> originalList.sortedBy { it.population }
            CountrySortOptions.DEFAULT -> originalList
        }
        submitList(sortedList)
        notifyDataSetChanged()
    }


    inner class CountryViewHolder constructor(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
                DetailsActivity.startActivity(binding.parent, getItem(position))
            }
        }

        fun bindCountry(country: Country) {
            binding.country = country
            binding.executePendingBindings()
        }
    }
}
