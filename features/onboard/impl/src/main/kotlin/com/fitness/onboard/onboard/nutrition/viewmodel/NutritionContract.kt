package com.fitness.onboard.onboard.nutrition.viewmodel

import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel

data class NutritionState(val step: NutritionStep = NutritionStep.NUTRITION_PREFERENCES)


sealed class NutritionEvent{
    data class HealthLabels(val labels: List<EHealthLabel>): NutritionEvent()
    data class DietaryRestrictions(val restrictions: List<EDietaryRestrictions>): NutritionEvent()
    data class CuisineType(val cuisineType: List<ECuisineType>): NutritionEvent()
    object SaveFitnessInfo: NutritionEvent()
}

enum class NutritionStep {
    NUTRITION_PREFERENCES,
    DIETARY_RESTRICTIONS,
    CUISINE_TYPE,
    SAVE_INFO,
    COMPLETE
}