package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodDataDto(
    val food: FoodDto? = null,
    val measures: List<MeasureDto?>? = null
)
