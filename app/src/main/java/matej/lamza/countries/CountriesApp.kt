package matej.lamza.countries

import android.app.Application
import matej.lamza.countries.di.CountriesDI

class CountriesApp : Application() {

    private val countriesDI: CountriesDI by lazy { CountriesDI(this) }
    override fun onCreate() {
        super.onCreate()
        countriesDI.init()
    }
}
