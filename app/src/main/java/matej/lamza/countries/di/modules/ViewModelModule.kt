package matej.lamza.countries.di.modules

import matej.lamza.core_model.Country
import matej.lamza.countries.ui.details.DetailsViewModel
import matej.lamza.countries.ui.search.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(countriesRepository = get()) }
    viewModel { (country: Country) -> DetailsViewModel(countriesRepository = get(), country) }
}
