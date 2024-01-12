package com.fitness.data.model.network.edamam.recipe


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientDto(
    val food: String?,
    val foodCategory: String?,
    val foodId: String?,
    val image: String?,
    val measure: String?,
    val quantity: Double?,
    val text: String?,
    val weight: Double?
)