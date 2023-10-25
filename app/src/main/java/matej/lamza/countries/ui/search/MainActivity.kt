package matej.lamza.countries.ui.search

import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private val countryVM by viewModel<CountryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            vm = countryVM
            adapter = CountryAdapter()
        }
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            countrySearch.editText.setOnEditorActionListener { textView, _, _ ->
                countryVM.submitSearchQuery(textView.text.toString())
                return@setOnEditorActionListener false
            }

            sort.setOnClickListener {
                val bottomSheetDialog = SortBottomDialog(this@MainActivity)
                bottomSheetDialog.show()
                bottomSheetDialog.onSortClicked = { sortOptions ->
                    binding.adapter?.sortCountries(sortOptions)
                }
            }
        }
    }
}
