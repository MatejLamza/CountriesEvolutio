package matej.lamza.countries.utils

import android.content.Context
import matej.lamza.countries.R
import java.net.UnknownHostException

object ErrorMapper {
    fun getMessage(throwable: Throwable?, context: Context): String {
        return when (throwable) {
            is UnknownHostException -> context.getString(R.string.Server_error_message)
            else -> context.getString(R.string.Uknown_error)
        }
    }
}
