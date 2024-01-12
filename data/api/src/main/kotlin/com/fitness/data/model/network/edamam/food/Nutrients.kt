package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nutrients(
    @Json(name = "CHOCDF") val chocdf: Double? = null,
    @Json(name = "ENERC_KCAL") val enerckcal: Double? = null,
    @Json(name = "FAT") val fat: Double? = null,
    @Json(name = "FIBTG") val fibtg: Double? = null,
    @Json(name = "PROCNT") val procnt: Double? = null
)