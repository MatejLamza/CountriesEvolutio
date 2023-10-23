package matej.lamza.core_network.model.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import matej.lamza.core_network.model.CountryErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<CountryErrorResponse> {

    /**
     *  Maps the [ApiResponse.Failure.Error] to the [CountryErrorResponse] using the mapper.
     */
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): CountryErrorResponse {
        return CountryErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}
