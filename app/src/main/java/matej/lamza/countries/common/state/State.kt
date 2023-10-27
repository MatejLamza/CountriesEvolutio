package matej.lamza.countries.common.state

sealed class State {
    object Loading : State()
    object Done : State()
    data class Error(val message: String? = null) : State()
}
