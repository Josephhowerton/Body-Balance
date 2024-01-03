package com.fitness.onboard.onboard.nutrition.viewmodel

import enums.EDietaryRestrictions
import enums.ENutritionPreferences

data class NutritionState(val step: NutritionStep = NutritionStep.NUTRITION_PREFERENCES)


sealed class NutritionEvent{
    data class NutritionPreferences(val preferences: List<ENutritionPreferences>): NutritionEvent()
    data class DietaryRestrictions(val restrictions: List<EDietaryRestrictions>): NutritionEvent()
    object SaveFitnessInfo: NutritionEvent()
}

enum class NutritionStep {
    NUTRITION_PREFERENCES,
    DIETARY_RESTRICTIONS,
    SAVE_INFO,
    COMPLETE
}