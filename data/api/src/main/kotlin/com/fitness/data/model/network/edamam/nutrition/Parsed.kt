package com.fitness.data.model.network.edamam.nutrition


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Parsed(
    val food: String? = null,
    val foodId: String? = null,
    val measure: String? = null,
    val measureURI: String? = null,
    val quantity: Double? = null,
    val retainedWeight: Double? = null,
    val status: String? = null,
    val weight: Double? = null
)