package com.fitness.data.model.domain.user

import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EGoals
import enums.EHealthLabel
import enums.ENutritionPreferences

data class UserBasicNutritionInfoDomain(
    val userId: String,
    val restrictions: List<EDietaryRestrictions>,
    val labels: List<EHealthLabel>,
    val cuisineType: List<ECuisineType>
)


