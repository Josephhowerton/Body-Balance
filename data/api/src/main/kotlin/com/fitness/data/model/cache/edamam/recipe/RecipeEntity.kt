package com.fitness.data.model.cache.edamam.recipe

import com.fitness.data.model.cache.edamam.shared.TotalDailyEntity
import com.fitness.data.model.cache.edamam.shared.TotalNutrientsEntity

data class RecipeEntity(
    val calories: Int,
    val cautions: List<String>,
    val co2EmissionsClass: String,
    val cuisineType: List<String>,
    val dietLabels: List<String>,
    val digest: List<DigestEntity>,
    val dishType: List<String>,
    val externalId: String,
    val glycemicIndex: Int,
    val healthLabels: List<String>,
    val image: String,
    val images: ImagesEntity,
    val ingredientLines: List<String>,
    val ingredients: List<IngredientEntity>,
    val instructions: List<String>,
    val label: String,
    val mealType: List<String>,
    val shareAs: String,
    val source: String,
    val tags: List<String>,
    val totalCO2Emissions: Int,
    val totalDaily: TotalDailyEntity,
    val totalNutrients: TotalNutrientsEntity,
    val totalWeight: Int,
    val uri: String,
    val url: String,
    val yield: Int
)