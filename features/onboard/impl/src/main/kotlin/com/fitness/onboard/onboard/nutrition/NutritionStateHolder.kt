package com.fitness.onboard.onboard.nutrition

import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel
import enums.ENutritionPreferences


object NutritionStateHolder {
    private var nutritionState: NutritionState = NutritionState()

    fun getState() = nutritionState
    fun updateState(newState: NutritionState) {
        nutritionState = newState
    }

    fun clearState() {
        nutritionState = NutritionState()
    }
}

data class NutritionState(
    val labels: List<EHealthLabel>? = null,
    val restrictions: List<EDietaryRestrictions>? = null,
    val cuisineType: List<ECuisineType>? = null
)
