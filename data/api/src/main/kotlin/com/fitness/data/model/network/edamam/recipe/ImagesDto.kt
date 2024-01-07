package com.fitness.data.model.network.edamam.recipe

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ImagesDto(
    @Json(name = "LARGE") val large: ImageMetaDataDto,
    @Json(name = "REGULAR") val regular: ImageMetaDataDto,
    @Json(name = "SMALL") val small: ImageMetaDataDto,
    @Json(name = "THUMBNAIL") val thumbnail: ImageMetaDataDto
) : Parcelable