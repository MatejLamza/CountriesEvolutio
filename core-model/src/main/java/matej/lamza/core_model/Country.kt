package matej.lamza.core_model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import matej.lamza.core_model.utils.extensions.getFormattedNumber


@Parcelize
data class Country(
    val name: String,
    val population: Int,
    val area: Double,
    val image: String,
    val capitals: List<String>?,
    val timezones: List<String>,
    val borders: List<String>?,
    val maps: CountryMapLinks
) : Parcelable {
    @IgnoredOnParcel
    val capital: String = capitals.toString()

    @IgnoredOnParcel
    val areaStr: String = "${area.toLong().getFormattedNumber()} km2"

    @IgnoredOnParcel
    val pop: String = population.toLong().getFormattedNumber()

    @IgnoredOnParcel
    val timezone = if (timezones.size > 1) {
        "${timezones.first()} - ${timezones.last()}"
    } else timezones.first()
}
