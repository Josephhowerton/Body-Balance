package com.fitness.onboard.onboard.fitness.view

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessStep
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FitnessLevelsPreview() = BodyBalanceTheme {
    Surface {
        FitnessLevels(state = FitnessState())
    }
}

@Composable
fun FitnessScreen(
    state: StateFlow<BaseViewState<FitnessState>>,
    onTriggerEvent: (FitnessEvent) -> Unit,
    onComplete: () -> Unit
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            Fitness(
                state = uiState.cast<BaseViewState.Data<FitnessState>>().value,
                onTriggerEvent = onTriggerEvent,
                onComplete = onComplete
            )
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as Failure

            ErrorScreen(title = failure.title, description = failure.description) {
                onComplete()
            }
        }

        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(message = R.string.unknown, onClick = onComplete)
        }
    }
}

@Composable
private fun Fitness(
    state: FitnessState,
    onTriggerEvent: (FitnessEvent) -> Unit = {},
    onComplete: () ->  Unit = {}
) {
    when(state.step){
        FitnessStep.FITNESS_LEVELS -> {
            FitnessLevels(state = state, onTriggerEvent = onTriggerEvent)
        }
        FitnessStep.HABITS -> {
            Habits(state = state,  onTriggerEvent = onTriggerEvent)
        }
        FitnessStep.GOALS -> {
            Goals(state = state, onTriggerEvent =  onTriggerEvent)
        }
        FitnessStep.SAVE_FITNESS_INFO -> onTriggerEvent(FitnessEvent.SaveFitnessInfo)
        FitnessStep.COMPLETE -> onComplete()
    }
}