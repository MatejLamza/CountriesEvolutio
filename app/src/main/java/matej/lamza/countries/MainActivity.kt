package matej.lamza.countries

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import matej.lamza.countries.ui.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val countryVM by viewModel<CountryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryVM.countries.observe(this) {
            Log.d("bbb", "Dobio sam listu zemalja: ${it.size}")
        }
    }
}
