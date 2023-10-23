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
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country

class CountryViewModel(private val countriesRepository: CountriesRepository) : BindingViewModel() {
    private val query = MutableStateFlow<String>("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val countriesList = query.flatMapConcat {
        countriesRepository.fetchCountriesForQuery(it)
    }.debounce(300)

    @get:Bindable
    val countryList: List<Country> by countriesList.asBindingProperty(viewModelScope, emptyList())

    fun setQuery(name: String) {
        query.value = name
    }
}
