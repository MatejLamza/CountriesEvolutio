package matej.lamza.countries.ui.search

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import matej.lamza.core_data.repository.CountriesRepository
import matej.lamza.core_model.Country
import matej.lamza.countries.common.state.State

class CountryViewModel(private val countriesRepository: CountriesRepository) : BindingViewModel() {
    private val query = MutableStateFlow<String>("")

    private val _uiState = MutableLiveData<State>()
    val uiState: LiveData<State> = _uiState

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val countriesList = query.flatMapConcat { query ->
        if (query.isNotBlank()) countriesRepository.fetchCountriesForQuery(query)
        else flowOf(emptyList())
    }

    @get:Bindable
    val countryList: List<Country> by countriesList.asBindingProperty(viewModelScope, emptyList())

    private val allCountriesFlow = countriesRepository.fetchCountriesList(
        { viewModelScope.launch { _uiState.value = State.Loading } },
        { viewModelScope.launch { _uiState.value = State.Done } },
        { viewModelScope.launch { _uiState.value = State.Error() } },
    )

    @get:Bindable
    val allCountries: List<Country> by allCountriesFlow.asBindingProperty(viewModelScope, emptyList())

    fun submitSearchQuery(name: String) {
        query.value = name
    }
}
