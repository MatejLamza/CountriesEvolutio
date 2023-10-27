package matej.lamza.countries.ui.search

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import matej.lamza.countries.databinding.DialogSortCountriesBinding
import matej.lamza.countries.utils.CountrySortOptions

class SortBottomDialog(context: Context) : BottomSheetDialog(context) {

    private var _binding: DialogSortCountriesBinding? = null
    val binding: DialogSortCountriesBinding
        get() = _binding!!

    var onSortClicked: ((options: CountrySortOptions) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogSortCountriesBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            sortAlphabetical.setOnClickListener {
                onSortClicked?.invoke(CountrySortOptions.ALPHABETICALLY)
                this@SortBottomDialog.dismiss()
            }
            sortArea.setOnClickListener {
                onSortClicked?.invoke(CountrySortOptions.AREA)
                this@SortBottomDialog.dismiss()
            }
            sortPopulation.setOnClickListener {
                onSortClicked?.invoke(CountrySortOptions.POPULATION)
                this@SortBottomDialog.dismiss()
            }
            sortDefault.setOnClickListener {
                onSortClicked?.invoke(CountrySortOptions.DEFAULT)
                this@SortBottomDialog.dismiss()
            }
        }
    }

    override fun onDetachedFromWindow() {
        _binding = null
        super.onDetachedFromWindow()
    }
}
