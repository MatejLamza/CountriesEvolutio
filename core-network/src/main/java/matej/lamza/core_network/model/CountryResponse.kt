package matej.lamza.core_network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryResponse(
    @field:Json(name = "name") val name: NameResponse,
    @field:Json(name = "population") val population: Int,
    @field:Json(name = "area") val area: Double,
    @field:Json(name = "flags") val flags: FlagsResponse,
    @field:Json(name = "capital") val capitals: List<String>,
    @field:Json(name = "timezones") val timezones: List<String>,
    @field:Json(name = "borders") val borders: List<String>
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
