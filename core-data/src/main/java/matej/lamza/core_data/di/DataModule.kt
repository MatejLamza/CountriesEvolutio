package matej.lamza.core_data.di

import matej.lamza.core_data.repository.CountriesRepoImpl
import matej.lamza.core_data.repository.CountriesRepository
import org.koin.dsl.module

val dataModule = module {
    single<CountriesRepository> { CountriesRepoImpl(countriesService = get()) }
}
