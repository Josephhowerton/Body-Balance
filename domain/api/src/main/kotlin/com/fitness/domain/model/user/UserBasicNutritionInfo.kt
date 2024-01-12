package com.fitness.domain.model.user

import com.fitness.data.extensions.toApiParamFromECuisineType
import com.fitness.data.extensions.toApiParamFromEHealthLabel
import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel

data class UserBasicNutritionInfo(
    val userId: String,
    val restrictions: List<EDietaryRestrictions>,
    val healthLabels: List<EHealthLabel>,
    val healthLabelsApi: List<String> = healthLabels.toApiParamFromEHealthLabel(),
    val cuisineType: List<ECuisineType>,
    val cuisineTypeApi: List<String> = cuisineType.toApiParamFromECuisineType(),
)


