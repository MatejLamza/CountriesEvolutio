package matej.lamza.core_network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryResponse(
    @field:Json(name = "common") val name: NameResponse,
    @field:Json(name = "capital") val capital: List<String>,
    @field:Json(name = "population") val population: Int,
    @field:Json(name = "area") val area: Double,
    @field:Json(name = "flags") val flags: List<String>,
)


data class NameResponse(
    @field:Json(name = "common") val name: String,
    @field:Json(name = "offical") val officalName: String,
)
