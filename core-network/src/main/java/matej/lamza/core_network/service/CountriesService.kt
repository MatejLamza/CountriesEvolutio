package matej.lamza.core_network.service

import com.skydoves.sandwich.ApiResponse
import matej.lamza.core_network.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("all")
    suspend fun fetchCountriesList(): ApiResponse<List<CountryResponse>>

    @GET("name/{name}")
    suspend fun fetchCountriesForQuery(@Path("name") name: String): ApiResponse<List<CountryResponse>>
}
