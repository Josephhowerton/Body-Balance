package com.fitness.data.model.network.edamam.nutrients

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class IngredientsDto(
    @Json(name = "parsed") val foodData: List<NutrientsFoodDto>
) : Parcelable