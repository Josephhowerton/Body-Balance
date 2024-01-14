package com.fitness.onboard.onboard.nutrition.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.component.screens.BalanceLazyColumn
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionEvent
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EDietaryRestrictions
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun DietaryRestrictionsPreview() = BodyBalanceTheme {
    Surface {
        DietaryRestrictions(NutritionState())
    }
}

@Composable
fun DietaryRestrictions(state: NutritionState, onTriggerEvent: (NutritionEvent) -> Unit = {}) = BalanceLazyColumn (
    title = R.string.title_dietary_restrictions,
    description = R.string.desc_dietary_restrictions,
    items = EDietaryRestrictions.values(),
    selections = state.restrictions.toMutableList(),
    onButtonClicked = { onTriggerEvent(NutritionEvent.DietaryRestrictions(restrictions = it)) }
)