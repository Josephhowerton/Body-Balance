package com.fitness.onboard.onboard.nutrition

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.fitness.component.screens.ErrorDialog
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.nutrition.view.CuisineType
import com.fitness.onboard.onboard.nutrition.view.DietaryRestrictions
import com.fitness.onboard.onboard.nutrition.view.HealthLabels
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
import failure.MinimumNumberOfSelectionFailure
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

            if(failure is MinimumNumberOfSelectionFailure){
                ErrorDialog(
                    title = stringResource(id = failure.title),
                    description = stringResource(id = failure.description, failure.minSelection),
                    onDismiss = { onTriggerEvent(NutritionEvent.DismissDialog) }
                )
            }
            else{
                ErrorScreen(title = failure.title, description = failure.description) {
                    if(failure is AuthStateFailure){
                        onForceSignOut()
                    }
                    else{
                        onComplete()
                    }
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
    Crossfade(targetState = state.step, label = "Nutrition") {
        when(it){
            NutritionStep.NUTRITION_PREFERENCES -> HealthLabels(state, onTriggerEvent = onTriggerEvent)
            NutritionStep.DIETARY_RESTRICTIONS -> DietaryRestrictions(state, onTriggerEvent = onTriggerEvent)
            NutritionStep.CUISINE_TYPE -> CuisineType(state, onTriggerEvent = onTriggerEvent)
            NutritionStep.SAVE_INFO -> onTriggerEvent(NutritionEvent.SaveFitnessInfo)
            NutritionStep.COMPLETE -> onComplete()
        }
    }
}