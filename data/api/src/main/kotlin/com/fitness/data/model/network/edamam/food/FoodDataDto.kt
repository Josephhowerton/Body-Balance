package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FoodDataDto(
    @Json(name = "food") val food: FoodDto,
    @Json(name = "measures") val measurement: List<MeasureDto>
) : Parcelable