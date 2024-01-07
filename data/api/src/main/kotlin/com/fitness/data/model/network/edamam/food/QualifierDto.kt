package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class QualifierDto(
    @Json(name = "label") val label: String,
    @Json(name = "uri") val uri: String
) : Parcelable