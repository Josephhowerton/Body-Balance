package com.fitness.recipebuilder.screens.date

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.BalanceDatePicker
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

@Light
@Dark
@Composable
private fun PreviewPickDate() = BodyBalanceTheme {
    Surface {
        PickDateContent(onTriggerEvent = {})
    }
}

@Composable
fun PickDate(
    state: StateFlow<BaseViewState<PickDateState>>,
    onTriggerEvent: (PickDateEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onPickTime: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<PickDateState>>().value
            if(currentState.step == RecipeBuilderStep.PENDING){
                PickDateContent(onTriggerEvent)
            }else{
                onPickTime()
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
private fun PickDateContent(onTriggerEvent: (PickDateEvent) -> Unit) {
    BalanceDatePicker(onDatesPicked = { onTriggerEvent(PickDateEvent.DateSelected(it)) })
}