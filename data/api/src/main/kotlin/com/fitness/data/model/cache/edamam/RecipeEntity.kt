package com.fitness.data.model.cache.edamam

import cache.generateUniqueId
data class RecipeEntity(
    val calories: Double? = null,
    val cautions: List<String>? = null,
    val co2EmissionsClass: String? = null,
    val cuisineType: List<String>? = null,
    val dietLabels: List<String>? = null,
    val digest: List<DigestEntity>? = null,
    val dishType: List<String>? = null,
    val healthLabels: List<String>? = null,
    val image: String? = null,
    val images: ImagesEntity? = null,
    val ingredientLines: List<String>? = null,
    val ingredients: List<IngredientEntity>? = null,
    val label: String? = null,
    val mealType: List<String>? = null,
    val shareAs: String? = null,
    val source: String? = null,
    val tags: List<String>? = null,
    val totalCO2Emissions: Double? = null,
    val totalDaily: TotalDailyEntity? = null,
    val totalNutrients: TotalNutrientsEntity? = null,
    val totalTime: Double? = null,
    val totalWeight: Double? = null,
    val uri: String? = null,
    val url: String? = null,
    val yield: Double? = null,
    val lastUpdated: Long = System.currentTimeMillis(),
    val id: String = generateUniqueId("$label-$source-$totalTime-$totalWeight"),
)