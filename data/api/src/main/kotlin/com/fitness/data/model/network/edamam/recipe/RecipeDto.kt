package com.fitness.data.model.network.edamam.recipe

import android.os.Parcelable
import com.fitness.model.remote.nutrition.shared.TotalDailyDto
import com.fitness.model.remote.nutrition.shared.TotalNutrientsDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RecipeDto(
    @Json(name = "calories") val calories: Int,
    @Json(name = "cautions") val cautions: List<String>,
    @Json(name = "co2EmissionsClass") val co2EmissionsClass: String,
    @Json(name = "cuisineType") val cuisineType: List<String>,
    @Json(name = "dietLabels") val dietLabels: List<String>,
    @Json(name = "digest") val digest: List<DigestDto>,
    @Json(name = "dishType") val dishType: List<String>,
    @Json(name = "externalId") val externalId: String,
    @Json(name = "glycemicIndex") val glycemicIndex: Int,
    @Json(name = "healthLabels") val healthLabels: List<String>,
    @Json(name = "image") val image: String,
    @Json(name = "images") val images: ImagesDto,
    @Json(name = "ingredientLines") val ingredientLines: List<String>,
    @Json(name = "ingredients") val ingredients: List<IngredientDto>,
    @Json(name = "instructions") val instructions: List<String>,
    @Json(name = "label") val label: String,
    @Json(name = "mealType") val mealType: List<String>,
    @Json(name = "shareAs") val shareAs: String,
    @Json(name = "source") val source: String,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "totalCO2Emissions") val totalCO2Emissions: Int,
    @Json(name = "totalDaily") val totalDaily: TotalDailyDto,
    @Json(name = "totalNutrients") val totalNutrients: TotalNutrientsDto,
    @Json(name = "totalWeight") val totalWeight: Int,
    @Json(name = "uri") val uri: String,
    @Json(name = "url") val url: String,
    @Json(name = "yield") val yield: Int
) : Parcelable