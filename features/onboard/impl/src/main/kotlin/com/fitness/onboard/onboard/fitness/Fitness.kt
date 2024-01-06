package com.fitness.onboard.onboard.fitness

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.fitness.view.FitnessLevels
import com.fitness.onboard.onboard.fitness.view.Habits
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessStep
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.cast
import failure.AuthStateFailure
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FitnessLevelsPreview() = BodyBalanceTheme {
    Surface {
        FitnessLevels()
    }
}

@Composable
fun FitnessScreen(
    state: StateFlow<BaseViewState<FitnessState>>,
    onTriggerEvent: (FitnessEvent) -> Unit,
    onComplete: () -> Unit,
    onForceSignOut: () -> Unit
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
                if(failure is AuthStateFailure){
                    onForceSignOut()
                }
                else{
                    onComplete()
                }
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
    Crossfade(targetState = state.step, label = "Fitness") {
        when(it){
            FitnessStep.FITNESS_LEVELS -> FitnessLevels(onTriggerEvent = onTriggerEvent)
            FitnessStep.HABITS -> Habits(onTriggerEvent = onTriggerEvent)
            FitnessStep.SAVE_FITNESS_INFO -> onTriggerEvent(FitnessEvent.SaveFitnessInfo)
            FitnessStep.COMPLETE -> onComplete()
        }
    }
}