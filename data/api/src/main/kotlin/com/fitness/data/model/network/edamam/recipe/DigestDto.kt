package com.fitness.data.model.network.edamam.recipe

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DigestDto(
    @Json(name = "daily") val daily: Int,
    @Json(name = "hasRDI") val hasRDI: Boolean,
    @Json(name = "label") val label: String,
    @Json(name = "schemaOrgTag") val schemaOrgTag: String,
    @Json(name = "sub") val sub: SubDto,
    @Json(name = "tag") val tag: String,
    @Json(name = "total") val total: Int,
    @Json(name = "unit") val unit: String
) : Parcelable