package matej.lamza.core_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryMapLinks(
    val googleMapLink: String,
    val openStreetLink: String,
) : Parcelable
