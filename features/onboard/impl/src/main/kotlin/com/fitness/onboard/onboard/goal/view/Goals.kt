package com.fitness.onboard.onboard.goal.view

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.fitness.component.screens.BalanceLazyColumn
import com.fitness.component.screens.ErrorDialog
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.onboard.onboard.goal.viewmodel.GoalEvent
import com.fitness.onboard.onboard.goal.viewmodel.GoalState
import com.fitness.onboard.onboard.goal.viewmodel.GoalStep
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EGoals
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
private fun GoalsPreview() = BodyBalanceTheme {
    Surface {
        Goals(state = GoalState())
    }
}

@Composable
fun GoalsScreen(
    state: StateFlow<BaseViewState<GoalState>> = MutableStateFlow(BaseViewState.Data(GoalState())),
    onTriggerEvent: (GoalEvent) -> Unit,
    onForceSignOut: () -> Unit
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            Goals(
                state = uiState.cast<BaseViewState.Data<GoalState>>().value,
                onTriggerEvent = onTriggerEvent,
            )
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as Failure

            if(failure is MinimumNumberOfSelectionFailure){
                ErrorDialog(
                    title = stringResource(id = failure.title),
                    description = stringResource(id = failure.description, failure.minSelection),
                    onDismiss = { onTriggerEvent(GoalEvent.DismissDialog) }
                )
            }
            else {
                ErrorScreen(title = failure.title, description = failure.description) {
                    if (failure is AuthStateFailure) {
                        onForceSignOut()
                    } else {
                        onTriggerEvent(GoalEvent.ForceComplete)
                    }
                }
            }
        }

        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(
                message = R.string.unknown,
                onClick = { onTriggerEvent(GoalEvent.ForceComplete) })
        }
    }
}

@Composable
private fun Goals(
    state: GoalState,
    onTriggerEvent: (GoalEvent) -> Unit = {},
) {
    Crossfade(targetState = state.step, label = "Fitness") {
        when (it) {
            GoalStep.GOALS -> GoalsContent(state, onTriggerEvent = onTriggerEvent)
            GoalStep.SAVE_INFO -> onTriggerEvent(GoalEvent.SaveInfo)
        }
    }
}

@Composable
private fun GoalsContent(state: GoalState, onTriggerEvent: (GoalEvent) -> Unit = {}) = BalanceLazyColumn (
    title = R.string.title_personal_health_goals,
    description = R.string.desc_personal_health_goals,
    items = EGoals.values(),
    selections = state.goals.toMutableList(),
    onButtonClicked = { onTriggerEvent(GoalEvent.Goals(goals = it)) }
)