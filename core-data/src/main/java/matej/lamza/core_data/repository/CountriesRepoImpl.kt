package matej.lamza.core_data.repository

import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import matej.lamza.core_model.Country
import matej.lamza.core_model.mapper.asDomain
import matej.lamza.core_network.service.CountriesService

class CountriesRepoImpl(private val countriesService: CountriesService) : CountriesRepository {

    override fun fetchCountriesList(
        onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit
    ): Flow<List<Country>> = flow {
        countriesService.fetchCountriesList()
            .suspendOnSuccess { emit(this.data.asDomain()) }
            .onFailure { onError(this.message()) }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(Dispatchers.IO)
}

