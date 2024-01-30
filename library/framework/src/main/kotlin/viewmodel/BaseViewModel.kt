package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import state.DataState

abstract class BaseViewModel : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {
        exception.printStackTrace()
    }
    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context = exceptionHandler, block = block)

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow.catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        executionFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        executionFlow.onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Success -> completionHandler.invoke(state.result)
                    is DataState.Error -> handleError(state.error)
                }
            }
    }
}