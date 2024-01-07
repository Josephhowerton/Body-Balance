package com.fitness.model.remote.nutrition.shared

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TotalDailyDto(
    @Json(name = "CA") val CalciumCa: NutrientsMetaDataDto,
    @Json(name = "CHOCDF") val CarbohydrateDifference: NutrientsMetaDataDto,
    @Json(name = "CHOLE") val Cholesterol: NutrientsMetaDataDto,
    @Json(name = "ENERC_KCAL") val Energy: NutrientsMetaDataDto,
    @Json(name = "FASAT") val FattyAcidsSaturated: NutrientsMetaDataDto,
    @Json(name = "FAT") val Fat: NutrientsMetaDataDto,
    @Json(name = "FE") val Iron: NutrientsMetaDataDto,
    @Json(name = "FIBTG") val FiberDietary: NutrientsMetaDataDto,
    @Json(name = "FOLDFE") val FolateDFE: NutrientsMetaDataDto,
    @Json(name = "K") val Potassium: NutrientsMetaDataDto,
    @Json(name = "MG") val Magnesium: NutrientsMetaDataDto,
    @Json(name = "NA") val Sodium: NutrientsMetaDataDto,
    @Json(name = "NIA") val Niacin: NutrientsMetaDataDto,
    @Json(name = "P") val Phosphorus: NutrientsMetaDataDto,
    @Json(name = "PROCNT") val Protein: NutrientsMetaDataDto,
    @Json(name = "RIBF") val Riboflavin: NutrientsMetaDataDto,
    @Json(name = "THIA") val Thiamin: NutrientsMetaDataDto,
    @Json(name = "TOCPHA") val VitaminE: NutrientsMetaDataDto,
    @Json(name = "VITA_RAE") val VitaminARAE: NutrientsMetaDataDto,
    @Json(name = "VITB12") val VitaminB12: NutrientsMetaDataDto,
    @Json(name = "VITB6A") val VitaminB6: NutrientsMetaDataDto,
    @Json(name = "VITC") val VitaminC: NutrientsMetaDataDto,
    @Json(name = "VITD") val VitaminD: NutrientsMetaDataDto,
    @Json(name = "VITK1") val VitaminK: NutrientsMetaDataDto,
    @Json(name = "ZN") val Zinc: NutrientsMetaDataDto
) : Parcelable