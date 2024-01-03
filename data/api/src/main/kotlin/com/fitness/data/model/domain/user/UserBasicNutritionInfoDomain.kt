package com.fitness.data.model.domain.user

import enums.EDietaryRestrictions
import enums.EGoals
import enums.ENutritionPreferences

data class UserBasicNutritionInfoDomain(
    val userId: String,
    val restrictions: List<EDietaryRestrictions>,
    val preferences: List<ENutritionPreferences>
)


