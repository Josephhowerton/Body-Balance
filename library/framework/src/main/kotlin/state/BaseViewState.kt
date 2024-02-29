package state

sealed interface BaseViewState<out T> {
    object Loading: BaseViewState<Nothing>
    object Empty: BaseViewState<Nothing>
    data class Data<T>(val value: T): BaseViewState<T>
    data class Error(val failure: Throwable) : BaseViewState<Nothing>
}