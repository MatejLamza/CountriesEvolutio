package matej.lamza.core_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Country(
    val name: String,
    val population: Int,
    val area: Double,
    val image: String?
) : Parcelable
