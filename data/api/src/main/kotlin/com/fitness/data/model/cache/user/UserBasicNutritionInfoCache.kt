package com.fitness.data.model.cache.user

data class UserBasicNutritionInfoCache(
    val userId: String = "",
    val restrictions: List<String> = emptyList(),
    val healthLabels: List<String> = emptyList(),
    val healthLabelsApi: List<String> = emptyList(),
    val cuisineType: List<String> = emptyList(),
    val cuisineTypeApi: List<String> = emptyList(),
    val lastUpdated: Long = 0L,
)