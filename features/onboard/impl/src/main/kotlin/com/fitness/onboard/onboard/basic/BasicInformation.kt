package com.fitness.onboard.onboard.basic

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.basic.view.GenderAge
import com.fitness.onboard.onboard.basic.view.HeightMeasurement
import com.fitness.onboard.onboard.basic.view.WeightMeasurement
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationState
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationStep
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.AuthStateFailure
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Light
@Dark
@Composable
private fun BasicInformationPreview() = BodyBalanceTheme {
    Surface {
        BasicInformationContent(state = BasicInformationState())
    }
}

@Composable
fun BasicInformationScreen(
    stateFlow: StateFlow<BaseViewState<BasicInformationState>>,
    onTriggerEvent: (BasicInformationEvent) -> Unit,
    onComplete: () -> Unit,
    onForceSignOut: () -> Unit
) {
    val uiState by stateFlow.collectAsState()

    Crossfade(
        targetState = uiState,
        animationSpec = tween(durationMillis = 1000),
        label = "BasicInformationContent"
    ) { state ->
        when (state) {
            is BaseViewState.Data -> {
                BasicInformationContent(
                    state = state.cast<BaseViewState.Data<BasicInformationState>>().value,
                    onTriggerEvent = onTriggerEvent,
                    onComplete = onComplete
                )
            }

            is BaseViewState.Error -> {
                val failure = state.cast<BaseViewState.Error>().throwable as Failure
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
}

@Composable
private fun BasicInformationContent(
    state: BasicInformationState,
    onTriggerEvent: (BasicInformationEvent) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    Crossfade(
        targetState = state.step,
        animationSpec = tween(durationMillis = 1000),
        label = "BasicInformationContent"
    ) { step ->
        when (step) {
            BasicInformationStep.GENDER_AGE -> GenderAge(onTriggerEvent = onTriggerEvent)
            BasicInformationStep.WEIGHT -> WeightMeasurement(onTriggerEvent = onTriggerEvent)
            BasicInformationStep.HEIGHT -> HeightMeasurement(onTriggerEvent = onTriggerEvent)
            BasicInformationStep.SAVE_BASIC_INFORMATION -> onTriggerEvent(BasicInformationEvent.SaveBasicInformation)
            BasicInformationStep.COMPLETE -> onComplete()
        }
    }
}