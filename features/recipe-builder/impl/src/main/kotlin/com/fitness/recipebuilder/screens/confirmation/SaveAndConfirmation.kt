package com.fitness.recipebuilder.screens.confirmation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState
import util.convertLongToDate
import util.formatTimeWithAmPm


@Light
@Dark
@Composable
private fun PreviewConfirmation() = BodyBalanceTheme {
    Surface {
        ConfirmationContent(state = SaveAndConfirmState(), onComplete = {})
    }
}

@Composable
fun SaveAndConfirm(
    state: StateFlow<BaseViewState<SaveAndConfirmState>>,
    onTriggerEvent: (SaveAndConfirmEvent) -> Unit,
    onComplete: () -> Unit = {},
    onPopBack: () -> Unit = {},
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<SaveAndConfirmState>>().value
            if(currentState.step == RecipeBuilderStep.PENDING){
                onTriggerEvent(SaveAndConfirmEvent.Save)
            }else{
                ConfirmationContent(uiState.cast<BaseViewState.Data<SaveAndConfirmState>>().value, onComplete)
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
private fun ConfirmationContent(
    state: SaveAndConfirmState,
    onComplete: () -> Unit
) {
    MessageScreen(
        message = stringResource(
            id = R.string.meal_success_message, convertLongToDate(state.date),
            formatTimeWithAmPm(state.hour, state.minute)
        ),
        onClick = onComplete
    )
}