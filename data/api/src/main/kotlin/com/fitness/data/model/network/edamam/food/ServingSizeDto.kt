package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ServingSizeDto(
    @Json(name = "label") val unit: String,
    @Json(name = "quantity") val quantity: Double,
    @Json(name = "uri") val unitUri: String
) : Parcelable