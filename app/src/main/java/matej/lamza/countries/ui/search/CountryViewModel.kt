package matej.lamza.countries.ui.search

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country

private const val INPUT_WAIT_DURATION = 500L

class CountryViewModel(private val countriesRepository: CountriesRepository) : BindingViewModel() {
    private val query = MutableStateFlow<String>("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val countriesList =
        query.flatMapConcat { query ->
            if (query.isNotBlank())
                countriesRepository.fetchCountriesForQuery(query)
            else flowOf(emptyList())
        }.debounce(INPUT_WAIT_DURATION)

    @get:Bindable
    val countryList: List<Country> by countriesList.asBindingProperty(viewModelScope, emptyList())

    fun submitSearchQuery(name: String) {
        query.value = name
    }
}
