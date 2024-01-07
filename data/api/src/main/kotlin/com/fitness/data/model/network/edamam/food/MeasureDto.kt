package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MeasureDto(
    @Json(name = "label") val unit: String,
    @Json(name = "qualifiedDto") val qualifiedDto: List<QualifiedDto>,
    @Json(name = "uri") val unitUri: String,
    @Json(name = "weight") val weight: Double
) : Parcelable