package matej.lamza.core_model.mapper

import matej.lamza.core_model.Country
import matej.lamza.core_network.model.CountryResponse

object CountryDomainMapper : DomainMapper<List<Country>, List<CountryResponse>> {
    override fun asDomain(response: List<CountryResponse>): List<Country> {
        return response.map {
            return@map with(it) {
                Country(
                    name.name, capital, population, area, flags.first()
                )
            }
        }
    }
}

fun List<CountryResponse>.asDomain(): List<Country> = CountryDomainMapper.asDomain(this)
