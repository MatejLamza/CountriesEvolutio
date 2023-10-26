package matej.lamza.core_model.utils.extensions

import java.util.Locale

private const val BILLION = 1000000000.0
private const val MILLION = 1000000.0
private const val HUNDRED_THOUSAND = 100000.0
private const val THOUSAND = 1000.0

fun Long.getFormattedNumber(): String {
    val number = this
    return when {
        number >= BILLION -> String.format(Locale.ENGLISH, "%.2fB", number / BILLION)
        number >= MILLION -> String.format(Locale.ENGLISH, "%.2fM", number / MILLION)
        number >= HUNDRED_THOUSAND -> String.format(Locale.ENGLISH, "%.2fK", number / THOUSAND)
        number >= THOUSAND -> String.format(Locale.ENGLISH, "%.2fK", number / THOUSAND)
        else -> number.toString()
    }
}
