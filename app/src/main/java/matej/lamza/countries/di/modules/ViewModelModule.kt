package matej.lamza.countries.di.modules

import matej.lamza.countries.ui.details.DetailsViewModel
import matej.lamza.countries.ui.search.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(countriesRepository = get()) }
    viewModel { DetailsViewModel(countriesRepository = get()) }
}
