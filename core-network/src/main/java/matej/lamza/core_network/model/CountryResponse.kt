package matej.lamza.core_network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryResponse(
    @field:Json(name = "name") val name: NameResponse,
    @field:Json(name = "population") val population: Int,
    @field:Json(name = "area") val area: Double,
    @field:Json(name = "flags") val flags: FlagsResponse
)

@JsonClass(generateAdapter = true)
data class NameResponse(
    @field:Json(name = "common") val name: String,
    @field:Json(name = "official") val officalName: String,
)

@JsonClass(generateAdapter = true)
data class FlagsResponse(
    @field:Json(name = "svg") val flagImage: String
)
