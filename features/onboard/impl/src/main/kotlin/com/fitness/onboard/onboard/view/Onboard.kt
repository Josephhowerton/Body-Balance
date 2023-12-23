package com.fitness.onboard.onboard.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import auth.AuthFailure
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.viewmodel.OnboardState
import com.fitness.resources.R
import extensions.cast
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Composable
fun OnboardScreen(
    state: StateFlow<BaseViewState<OnboardState>>,
    onErrorEvent: (AuthFailure) -> Unit,
    onPopBack: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {}

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onErrorEvent.invoke(failure)
            }
        }

        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(message = R.string.unknown, onClick = onPopBack)
        }
    }

}
