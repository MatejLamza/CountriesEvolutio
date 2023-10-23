package matej.lamza.countries.di.modules

import matej.lamza.countries.ui.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(countriesRepository = get()) }
}
