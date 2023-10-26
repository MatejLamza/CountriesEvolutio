package matej.lamza.countries.ui.search

import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivitySearchBinding
import matej.lamza.countries.utils.extensions.handleUIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private val countryVM by viewModel<CountryViewModel>()
    private val sortDialog by lazy { SortBottomDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            vm = countryVM
            queryResultAdapter = CountryAdapter()
            allCountriesAdapter = CountryAdapter()
        }
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        with(binding) {
            countrySearch.editText.setOnEditorActionListener { textView, _, _ ->
                countryVM.submitSearchQuery(textView.text.toString())
                return@setOnEditorActionListener false
            }
            sort.setOnClickListener { sortDialog.show() }
            sortDialog.onSortClicked = { criteria ->
                binding.queryResultAdapter?.sortCountries(criteria)
            }
        }
    }

    private fun setupObservers() {
        countryVM.uiState.observe(this) { uiState ->
            uiState.handleUIState(binding.root, binding.shimmerContainer)
        }
    }
}
