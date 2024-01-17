package com.fitness.search.screens.time

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.BalanceTimePicker
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.search.SearchRecipeStep
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Light
@Dark
@Composable
private fun PreviewPickTime() = BodyBalanceTheme {
    Surface {
        PickTimeContent(onTriggerEvent = {})
    }
}

@Composable
fun PickTime(
    state: StateFlow<BaseViewState<PickTimeState>>,
    onTriggerEvent: (PickTimeEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onConfirmation: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<PickTimeState>>().value
            if(currentState.step == SearchRecipeStep.PENDING){
                PickTimeContent(onTriggerEvent)
            }else{
                onConfirmation()
            }

        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as Failure

            ErrorScreen(title = failure.title, description = failure.description) {
                onPopBack()
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

@Composable
private fun PickTimeContent(onTriggerEvent: (PickTimeEvent) -> Unit) {
    BalanceTimePicker(
        onTimePicked = {hour, minute ->
            onTriggerEvent(PickTimeEvent.TimeSelected(hour = hour, minute = minute))
        }
    )
}