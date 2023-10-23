package matej.lamza.countries.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country

class CountryViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        viewModelScope.launch {
            countriesRepository.fetchCountriesList({
                Log.d("bbb", "Started... ")
            }, {
                Log.d("bbb", "Completed: ")
            }, {
                Log.e("bbb", "Error happen:$it")
            }).collect {
                _countries.value = it
            }
        }
    }
}
