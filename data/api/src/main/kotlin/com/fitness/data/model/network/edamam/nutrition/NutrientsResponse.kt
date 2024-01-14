package com.fitness.data.model.network.edamam.nutrition


import com.fitness.data.model.network.edamam.shared.TotalNutrientsDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NutrientsResponse(
    val calories: Int? = null,
    val cautions: List<String>? = null,
    val dietLabels: List<String>? = null,
    val healthLabels: List<String>? = null,
    val totalNutrients: TotalNutrientsDto? = null,
    val totalWeight: Double? = null,
    val uri: String? = null
)