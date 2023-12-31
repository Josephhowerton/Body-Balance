package com.fitness.onboard.onboard.fitness.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun PreviewFitnessLevels() = BodyBalanceTheme {
    Surface {
        FitnessLevels(FitnessState())
    }
}



@Composable
fun FitnessLevels(
    state: FitnessState,
    onTriggerEvent: (FitnessEvent) -> Unit = {},
){

}