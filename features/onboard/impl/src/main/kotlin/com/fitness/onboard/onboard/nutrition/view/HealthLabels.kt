package com.fitness.onboard.onboard.nutrition.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.component.components.BalanceLazyColumn
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionEvent
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EHealthLabel
import extensions.Dark
import extensions.Light


@Light
@Dark
@Composable
private fun HealthLabelsPreview() = BodyBalanceTheme {
    Surface {
        HealthLabels(NutritionState())
    }
}

@Composable
fun HealthLabels(state: NutritionState,  onTriggerEvent: (NutritionEvent) -> Unit = {})  = BalanceLazyColumn (
    title = R.string.onboarding_health_labels_title,
    description = R.string.onboarding_health_labels_description,
    items = EHealthLabel.values(),
    selections = state.labels.toMutableList(),
    onButtonClicked = { onTriggerEvent(NutritionEvent.HealthLabels(labels = it)) }
)