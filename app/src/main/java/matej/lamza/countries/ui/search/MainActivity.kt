package matej.lamza.countries.ui.search

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.skydoves.bindables.BindingActivity
import kotlinx.coroutines.launch
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private val countryVM by viewModel<CountryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            vm = countryVM
        }

        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            countrySearch.editText.setOnEditorActionListener { textView, _, _ ->
                countryVM.setQuery(textView.text.toString())
                return@setOnEditorActionListener false
            }

            lifecycleScope.launch {
                countryVM.countriesList.collect {
                    it.forEach {
                        Log.d("bbb", "$it")
                    }
                }
            }

            recyclerView.adapter = CountryAdapter()
        }
    }
}
