package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class NutrientsDto(
    @Json(name = "CA") val CalciumCa: Double,
    @Json(name = "CHOCDF") val CarbohydrateDifference: Double,
    @Json(name = "CHOLE") val Cholesterol: Double,
    @Json(name = "ENERC_KCAL") val Energy: Double,
    @Json(name = "FAMS") val FattyAcidsMonounsaturated: Double,
    @Json(name = "FAPU") val FattyAcidsPolyunsaturated: Double,
    @Json(name = "FASAT") val FattyAcidsSaturated: Double,
    @Json(name = "FAT") val Fat: Double,
    @Json(name = "FATRN") val FatTrans: Double,
    @Json(name = "FE") val Iron: Double,
    @Json(name = "FIBTG") val FiberDietary: Double,
    @Json(name = "K") val Potassium: Double,
    @Json(name = "MG") val Magnesium: Double,
    @Json(name = "NA") val Sodium: Double,
    @Json(name = "NIA") val Niacin: Double,
    @Json(name = "P") val Phosphorus: Double,
    @Json(name = "PROCNT") val Protein: Double,
    @Json(name = "RIBF") val Riboflavin: Double,
    @Json(name = "SUGAR") val Sugar: Double,
    @Json(name = "THIA") val Thiamin: Double,
    @Json(name = "VITB12") val VitaminB12: Double,
    @Json(name = "VITB6A") val VitaminB6: Double,
    @Json(name = "VITC") val VitaminC: Double,
    @Json(name = "ZN") val Zinc: Double
) : Parcelable