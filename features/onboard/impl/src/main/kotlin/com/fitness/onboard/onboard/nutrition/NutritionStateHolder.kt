package com.fitness.onboard.onboard.nutrition

import enums.EDietaryRestrictions
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
    val preferences: List<ENutritionPreferences>? = null,
    val restrictions: List<EDietaryRestrictions>? = null
)
