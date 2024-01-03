package com.fitness.onboard.onboard.nutrition.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionEvent
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionState
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionStep
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.AuthStateFailure
import failure.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Light
@Dark
@Composable
private fun NutritionPreview() = BodyBalanceTheme {
    Surface {
        Nutrition(NutritionState())
    }
}

@Composable
fun NutritionScreen(
    state: StateFlow<BaseViewState<NutritionState>> = MutableStateFlow(BaseViewState.Data(NutritionState())),
    onTriggerEvent: (NutritionEvent) -> Unit,
    onComplete: () -> Unit,
    onForceSignOut: () -> Unit
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            Nutrition(
                state = uiState.cast<BaseViewState.Data<NutritionState>>().value,
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
private fun Nutrition(
    state: NutritionState,
    onTriggerEvent: (NutritionEvent) -> Unit = {},
    onComplete: () ->  Unit = {}
) {
    when(state.step){
        NutritionStep.NUTRITION_PREFERENCES -> NutritionPreferences(onTriggerEvent = onTriggerEvent)
        NutritionStep.DIETARY_RESTRICTIONS -> DietaryRestrictions(onTriggerEvent = onTriggerEvent)
        NutritionStep.SAVE_INFO -> onTriggerEvent(NutritionEvent.SaveFitnessInfo)
        NutritionStep.COMPLETE -> onComplete()
    }
}