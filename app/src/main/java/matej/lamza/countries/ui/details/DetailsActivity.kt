package matej.lamza.countries.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import matej.lamza.core_model.Country
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BindingActivity<ActivityDetailsBinding>(R.layout.activity_details) {

    private val country: Country by bundleNonNull(EXTRA_COUNTRY)
    private val detailsVM by viewModel<DetailsViewModel>()
    private val adapter by lazy { CountryBordersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            country = this@DetailsActivity.country
            adapter = this@DetailsActivity.adapter
        }
        country.borders?.let { adapter.addBorderList(it) }
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
