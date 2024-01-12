package com.fitness.data.model.network.edamam.recipe

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitDto(
    val recipe: RecipeDto?
)