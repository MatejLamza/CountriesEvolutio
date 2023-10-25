package matej.lamza.countries.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.distinctUntilChanged
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import matej.lamza.core_model.Country
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivityDetailsBinding
import matej.lamza.countries.utils.extensions.openMaps
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BindingActivity<ActivityDetailsBinding>(R.layout.activity_details) {

    private val country: Country by bundleNonNull(EXTRA_COUNTRY)
    private val detailsVM by viewModel<DetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.country = country
        binding.btnNavigate.setOnClickListener { openMaps("") }
        binding.borders.setOnClickListener { detailsVM.getCountryByCode("BIH") }
        detailsVM.country.distinctUntilChanged().observe(this) { startActivity(binding.parent, it) }
    }

    companion object {
        internal const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
        fun startActivity(constraintLayout: ConstraintLayout, country: Country) {
            constraintLayout.context.intentOf<DetailsActivity> {
                putExtra(EXTRA_COUNTRY to country)
                setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(constraintLayout.context)
            }
        }
    }
}
