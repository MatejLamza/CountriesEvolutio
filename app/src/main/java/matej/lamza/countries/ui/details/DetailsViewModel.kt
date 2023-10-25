package matej.lamza.countries.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country

class DetailsViewModel(private val countriesRepository: CountriesRepository) : BindingViewModel() {

    private var _country = MutableLiveData<Country>()
    val country: LiveData<Country> = _country

    fun getCountryByCode(code: String) {
        viewModelScope.launch {
            _country.value = countriesRepository.fetchCountryByCode(code).first()
        }
    }
}
