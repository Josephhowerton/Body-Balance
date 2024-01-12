package com.fitness.data.model.network.edamam.recipe

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class NutrientsMetaDataDto(
    @Json(name = "label") val label: String?,
    @Json(name = "quantity") val quantity: Double?,
    @Json(name = "unit") val unit: String?
) : Parcelable