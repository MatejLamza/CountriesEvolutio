package matej.lamza.core_data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import matej.lamza.core_model.Country

interface CountriesRepository {
    @WorkerThread
    fun fetchCountriesList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Country>>

    @WorkerThread
    fun fetchCountriesForQuery(name: String): Flow<List<Country>>
}
