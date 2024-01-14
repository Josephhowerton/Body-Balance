package com.fitness.data.model.network.edamam.recipe


import com.fitness.data.model.network.edamam.shared.TotalDailyDto
import com.fitness.data.model.network.edamam.shared.TotalNutrientsDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
    val calories: Double?,
    val cautions: List<String>?,
    val co2EmissionsClass: String?,
    val cuisineType: List<String>?,
    val dietLabels: List<String>?,
    val digest: List<DigestDto>?,
    val dishType: List<String>?,
    val healthLabels: List<String>?,
    val image: String?,
    val images: ImagesDto?,
    val ingredientLines: List<String>?,
    val ingredients: List<IngredientDto>?,
    val label: String?,
    val mealType: List<String>?,
    val shareAs: String?,
    val source: String?,
    val tags: List<String>?,
    val totalDaily: TotalDailyDto?,
    val totalNutrients: TotalNutrientsDto?,
    val totalTime: Double?,
    val totalWeight: Double?,
    val uri: String?,
    val url: String?,
    val yield: Double?
)