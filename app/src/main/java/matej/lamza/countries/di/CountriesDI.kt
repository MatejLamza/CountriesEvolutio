package matej.lamza.countries.di

import android.app.Application
import matej.lamza.core_data.di.dataModule
import matej.lamza.core_network.di.networkModule
import matej.lamza.countries.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CountriesDI(private val application: Application) {

    private lateinit var koinApplication: KoinApplication
    private val modules = listOf(
        dataModule,
        networkModule,
        viewModelModule
    )

    fun init() {
        koinApplication = startKoin {
            androidContext(application)
            modules(modules)
            androidLogger(Level.INFO)
        }
    }
}
