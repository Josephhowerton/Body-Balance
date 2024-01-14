package com.fitness.onboard.onboard.nutrition.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.component.screens.BalanceLazyColumn
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionEvent
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.ECuisineType
import extensions.Dark
import extensions.Light


@Light
@Dark
@Composable
private fun CuisineTypePreview() = BodyBalanceTheme {
    Surface {
        CuisineType(NutritionState())
    }
}

@Composable
fun CuisineType(state: NutritionState, onTriggerEvent: (NutritionEvent) -> Unit = {}) = BalanceLazyColumn (
    title = R.string.onboarding_cuisine_types_title,
    description = R.string.onboarding_cuisine_types_description,
    items = ECuisineType.values(),
    selections = state.cuisineType.toMutableList(),
    onButtonClicked = { onTriggerEvent(NutritionEvent.CuisineType(cuisineType = it)) }
)
