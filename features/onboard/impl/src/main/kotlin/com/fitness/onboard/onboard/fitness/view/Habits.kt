package com.fitness.onboard.onboard.fitness.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.component.screens.BalanceLazyColumn
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EFitnessInterest
import extensions.Dark
import extensions.Light


@Light
@Dark
@Composable
private fun PreviewHabitsContent() = BodyBalanceTheme {
    Surface {
        Habits(FitnessState())
    }
}


@Composable
fun Habits(state: FitnessState, onTriggerEvent: (FitnessEvent) -> Unit = {}) =
    BalanceLazyColumn(
        title = R.string.title_fitness_habits,
        description = R.string.desc_fitness_habits,
        items = EFitnessInterest.values(),
        selections = state.habits.toMutableList(),
        onButtonClicked = {
            onTriggerEvent(FitnessEvent.Habits(habits = it))
        }
    )