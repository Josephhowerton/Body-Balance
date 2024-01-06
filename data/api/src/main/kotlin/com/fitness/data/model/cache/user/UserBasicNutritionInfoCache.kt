package com.fitness.data.model.cache.user

import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel

data class UserBasicNutritionInfoCache(
    val userId: String,
    val restrictions: List<EDietaryRestrictions>,
    val labels: List<EHealthLabel>,
    val cuisineType: List<ECuisineType>,
    val lastUpdated: Long,
)