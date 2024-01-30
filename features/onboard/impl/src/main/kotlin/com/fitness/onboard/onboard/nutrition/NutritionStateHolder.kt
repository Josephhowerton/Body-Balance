package com.fitness.onboard.onboard.nutrition

import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionStep
import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel


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
    val currentStep: NutritionStep = NutritionStep.NUTRITION_PREFERENCES,
    val labels: List<EHealthLabel> = emptyList(),
    val restrictions: List<EDietaryRestrictions> = emptyList(),
    val cuisineType: List<ECuisineType> = emptyList()
)
