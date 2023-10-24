package matej.lamza.countries.ui.details

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import matej.lamza.core_model.Country
import matej.lamza.countries.R
import matej.lamza.countries.databinding.ActivityDetailsBinding
import matej.lamza.countries.utils.extensions.openMaps


class DetailsActivity : BindingActivity<ActivityDetailsBinding>(R.layout.activity_details) {

    private val country: Country by bundleNonNull(EXTRA_COUNTRY)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.country = country
        binding.filledButton.setOnClickListener {
            openMaps("")
        }
    }

    companion object {
        internal const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
        fun startActivity(constraintLayout: ConstraintLayout, country: Country) {
            constraintLayout.context.intentOf<DetailsActivity> {
                putExtra(EXTRA_COUNTRY to country)
                startActivity(constraintLayout.context)
            }
        }
    }
}
