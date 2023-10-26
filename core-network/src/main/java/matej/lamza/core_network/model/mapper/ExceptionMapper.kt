package matej.lamza.core_network.model.mapper

import java.net.UnknownHostException

object ExceptionMapper {
    fun getMessage(throwable: Throwable?): String {
        return when (throwable) {
            is UnknownHostException -> "Please check your internet connection!"
            else -> "Unknown error occurred"
        }
    }
}
