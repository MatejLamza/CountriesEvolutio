package matej.lamza.core_data.repository

import android.util.Log
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import matej.lamza.core_model.Country
import matej.lamza.core_model.mapper.asDomain
import matej.lamza.core_network.model.mapper.ErrorResponseMapper
import matej.lamza.core_network.model.mapper.ExceptionMapper
import matej.lamza.core_network.service.CountriesService

private const val TAG = "RepoImpl"

class CountriesRepoImpl(private val countriesService: CountriesService) : CountriesRepository {

    override fun fetchCountriesList(
        onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit
    ): Flow<List<Country>> = flow {
        countriesService.fetchCountriesList()
            .suspendOnSuccess { emit(this.data.asDomain()) }
            .onError { map(ErrorResponseMapper) { onError("[Code: $code]: $message") } }
            .onException {
                Log.e(TAG, "fetchCountriesList", exception)
                onError(ExceptionMapper.getMessage(exception))
            }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(Dispatchers.IO)

    override fun fetchCountriesForQuery(
        name: String,
        onStart: (() -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: ((String?) -> Unit)
    ): Flow<List<Country>> =
        flow {
            countriesService.fetchCountriesForQuery(name)
                .suspendOnSuccess { emit(this.data.asDomain()) }
                //handles cases caused by API like internal server error
                .onError {
                    Log.d(TAG, "fetchCountriesForQueryErr: Error!")
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                //handles cases like internet connection error
                .onException {
                    Log.e(TAG, "fetchCountriesForQuery", exception)
                    onError(ExceptionMapper.getMessage(exception))
                }
        }
            .onStart { onStart?.invoke() }
            .onCompletion { onComplete?.invoke() }
            .flowOn(Dispatchers.IO)

    override fun fetchCountryByCode(code: String, onError: (String?) -> Unit): Flow<Country> =
        flow {
            countriesService.fetchCountryByCode(code)
                .suspendOnSuccess { emit(data.asDomain().first()) }
                .onError { map(ErrorResponseMapper) { onError("[Code: $code]: $message") } }
                .onException {
                    Log.e(TAG, "Error while fetching country by code", exception)
                    onError.invoke(message)
                }
        }.flowOn(Dispatchers.IO)
}

