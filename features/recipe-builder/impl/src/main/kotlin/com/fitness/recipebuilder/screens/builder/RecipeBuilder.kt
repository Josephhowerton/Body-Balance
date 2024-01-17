package com.fitness.recipebuilder.screens.builder

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
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
private fun PreviewRecipeBuilder() = BodyBalanceTheme {
    Surface {
        RecipeBuilderContent(state = RecipeBuilderState(), onTriggerEvent = {})
    }
}

@Composable
fun RecipeBuilder(
    state: StateFlow<BaseViewState<RecipeBuilderState>>,
    onTriggerEvent: (RecipeBuilderEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onPickDate: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {

        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<RecipeBuilderState>>().value
            if (currentState.step == RecipeBuilderStep.PENDING) {
                RecipeBuilderContent(state = currentState, onTriggerEvent = onTriggerEvent)
            } else {
                onPickDate()
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
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
private fun RecipeBuilderContent(
    state: RecipeBuilderState,
    onTriggerEvent: (RecipeBuilderEvent) -> Unit
) {
    val ime = WindowInsets.ime
    val insets = ime.asPaddingValues().calculateBottomPadding()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isImeVisible = WindowInsets.isImeVisible

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = if (isImeVisible) insets else 0.dp)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {}) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {


    }

}