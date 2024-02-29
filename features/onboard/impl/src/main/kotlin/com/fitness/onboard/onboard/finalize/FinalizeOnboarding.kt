package com.fitness.onboard.onboard.finalize

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.component.screens.ErrorDialog
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.fitness.Fitness
import com.fitness.onboard.onboard.fitness.view.FitnessLevels
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.cast
import failure.AuthStateFailure
import failure.Failure
import failure.MinimumNumberOfSelectionFailure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FinalizeOnboardingPreview() = BodyBalanceTheme {
    Surface {
        FitnessLevels()
    }
}

@Composable
fun FinalizeOnboardingScreen(
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
            val failure = uiState.cast<BaseViewState.Error>().failure as Failure

            if(failure is MinimumNumberOfSelectionFailure){
                ErrorDialog(
                    title = stringResource(id = failure.title),
                    description = stringResource(id = failure.description, failure.minSelection),
                    onDismiss = { onTriggerEvent(FitnessEvent.DismissDialog) }
                )
            }
            else {
                ErrorScreen(title = failure.title, description = failure.description) {
                    if (failure is AuthStateFailure) {
                        onForceSignOut()
                    } else {
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