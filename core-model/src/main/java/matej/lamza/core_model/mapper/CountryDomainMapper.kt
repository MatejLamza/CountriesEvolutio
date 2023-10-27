package matej.lamza.core_model.mapper

import matej.lamza.core_model.Country
import matej.lamza.core_model.CountryMapLinks
import matej.lamza.core_network.model.CountryResponse

object CountryDomainMapper : DomainMapper<List<Country>, List<CountryResponse>> {
    override fun asDomain(response: List<CountryResponse>): List<Country> {
        return response.map {
            return@map with(it) {
                Country(
                    name.name,
                    population,
                    area,
                    image = flags.flagImage,
                    capitals = capitals,
                    timezones,
                    borders,
                    CountryMapLinks(this.maps.googleMapsLink, this.maps.openStreetLink)
                )
            }
        }
    }
}

fun List<CountryResponse>.asDomain(): List<Country> = CountryDomainMapper.asDomain(this)
