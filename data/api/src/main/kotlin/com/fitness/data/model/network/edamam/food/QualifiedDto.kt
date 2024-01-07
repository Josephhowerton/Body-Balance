package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class QualifiedDto(
    @Json(name = "qualifiers") val qualifiers: List<QualifierDto>,
    @Json(name = "weight") val weight: Double
) : Parcelable