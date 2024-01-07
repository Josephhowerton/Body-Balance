package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class NextDto(
    @Json(name = "href" ) val href: String,
    @Json(name = "title" ) val title: String
) : Parcelable