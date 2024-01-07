package com.fitness.data.model.network.edamam.nutrients

import com.fitness.model.remote.nutrition.shared.TotalDailyDto
import com.fitness.model.remote.nutrition.shared.TotalNutrientsDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NutrientsResponse(
    @Json(name = "calories") val calories: Int,
    @Json(name = "cautions") val cautions: List<String>,
    @Json(name = "dietLabels") val dietLabels: List<String>,
    @Json(name = "healthLabels") val healthLabels: List<String>,
    @Json(name = "ingredients") val ingredients: List<IngredientsDto>,
    @Json(name = "totalDaily") val totalDaily: TotalDailyDto,
    @Json(name = "totalNutrients") val totalNutrients: TotalNutrientsDto,
    @Json(name = "totalWeight") val totalWeight: Int,
    @Json(name = "uri") val uri: String
)