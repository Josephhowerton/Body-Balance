package viewmodel

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import state.BaseViewState
import kotlin.reflect.KClass

abstract class IntentViewModel<STATE: BaseViewState<*>, EVENT> : BaseViewModel() {
    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState get() = _uiState.asStateFlow()


    abstract fun onTriggerEvent(event: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    protected fun <T> currentState() = (_uiState.value.cast<BaseViewState<T>>() as BaseViewState.Data).value

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        Firebase.crashlytics.recordException(exception)
        _uiState.value = BaseViewState.Error(exception)
    }
}