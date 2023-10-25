package matej.lamza.core_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Country(
    val name: String,
    val population: Int,
    val area: Double,
    val image: String,
    val capitals: List<String>,
    val timezones: List<String>
) : Parcelable {
    val capital: String = capitals.toString()
    val areaStr: String = area.toString()
    val pop: String = population.toString()
    val timezone = if (timezones.size > 1) {
        "${timezones.first()} - ${timezones.last()}"
    } else timezones.first()
}
