package matej.lamza.core_data.repository

import android.util.Log
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onException
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

private const val TAG = "RepoImpl"

class CountriesRepoImpl(private val countriesService: CountriesService) : CountriesRepository {

    override fun fetchCountriesList(
        onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit
    ): Flow<List<Country>> = flow {
        countriesService.fetchCountriesList()
            .suspendOnSuccess { emit(this.data.asDomain()) }
            .onFailure { onError(this.message()) }
            .onException { Log.d(TAG, "fetchCountriesList: ") }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(Dispatchers.IO)

    override fun fetchCountriesForQuery(
        name: String,
        onStart: (() -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: ((String?) -> Unit)?
    ): Flow<List<Country>> =
        flow {
            countriesService.fetchCountriesForQuery(name)
                .suspendOnSuccess { emit(this.data.asDomain()) }
                .onFailure {
                    Log.e(TAG, "fetchCountriesForQuery: $name Error!\n ${this.message()}")
                    onError?.invoke(this.message())
                }
                .onException { Log.d(TAG, "fetchCountriesForQuery: ") }
        }
            .onStart { onStart?.invoke() }
            .onCompletion { onComplete?.invoke() }
            .flowOn(Dispatchers.IO)

    override fun fetchCountryByCode(code: String, onError: (Throwable?) -> Unit): Flow<Country> =
        flow {
            countriesService.fetchCountryByCode(code)
                .suspendOnSuccess { emit(data.asDomain().first()) }
                .onFailure { Log.e(TAG, "fetchCountryByCode: $code Error! \n ${message()}") }
                .onException {
                    Log.d(TAG, "fetchCountryByCode: Exception ${this.exception}")
                    onError.invoke(exception)
                }
        }.flowOn(Dispatchers.IO)

}

