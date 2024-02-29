package com.fitness.recipebuilder.screens.mealtype

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.component.screens.SelectMealType
import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.resources.R
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
private fun PreviewPickMealType() = BodyBalanceTheme {
    Surface {
        PickMealTypeContent(onTriggerEvent = {})
    }
}

@Composable
fun PickMealType(
    state: StateFlow<BaseViewState<PickMealTypeState>>,
    onTriggerEvent: (PickMealTypeEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onConfirmation: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<PickMealTypeState>>().value
            if (currentState.step == RecipeBuilderStep.PENDING) {
                PickMealTypeContent(onTriggerEvent)
            } else {
                onConfirmation()
            }
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().failure as Failure

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
private fun PickMealTypeContent(onTriggerEvent: (PickMealTypeEvent) -> Unit) {
    SelectMealType(
        onConfirm = {
            onTriggerEvent(PickMealTypeEvent.MealTypeSelected(type = it))
        }
    )
}