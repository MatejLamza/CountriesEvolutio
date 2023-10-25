package matej.lamza.countries.ui.details

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country

class DetailsViewModel(
    private val countriesRepository: CountriesRepository, country: Country
) : BindingViewModel() {

    private var countryInfoFlow = MutableStateFlow<Country?>(null)

    @get:Bindable
    val countryInfo: Country? by countryInfoFlow.asBindingProperty(viewModelScope, null)

    init {
        viewModelScope.launch {
            countryInfoFlow.emit(countriesRepository.fetchCountriesForQuery(country.name).first().first())
        }
    }

    fun fetchNewCountry(code: String) {
        viewModelScope.launch {
            val newCountry = countriesRepository.fetchCountryByCode(code).first()
            countryInfoFlow.emit(newCountry)
        }
    }
}
