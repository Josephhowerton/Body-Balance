package com.fitness.data.model.cache.user

import enums.EDietaryRestrictions
import enums.ENutritionPreferences

data class UserBasicNutritionInfoCache(
    val userId: String,
    val restrictions: List<EDietaryRestrictions>,
    val preferences: List<ENutritionPreferences>,
    val lastUpdated: Long,
)