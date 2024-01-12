package com.fitness.data.model.network.edamam.recipe


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TotalNutrientsDto(
    @Json(name = "CA") val ca: NutrientsMetaDataDto?,
    @Json(name = "CHOCDF") val chocdf: NutrientsMetaDataDto?,
    @Json(name = "CHOCDF.net") val chocdfnet: NutrientsMetaDataDto?,
    @Json(name = "CHOLE") val chole: NutrientsMetaDataDto?,
    @Json(name = "ENERC_KCAL") val enerckcal: NutrientsMetaDataDto?,
    @Json(name = "FAMS") val fams: NutrientsMetaDataDto?,
    @Json(name = "FAPU") val fapu: NutrientsMetaDataDto?,
    @Json(name = "FASAT") val fasat: NutrientsMetaDataDto?,
    @Json(name = "FAT") val fat: NutrientsMetaDataDto?,
    @Json(name = "FATRN") val fatrn: NutrientsMetaDataDto?,
    @Json(name = "FE") val fe: NutrientsMetaDataDto?,
    @Json(name = "FIBTG") val fibtg: NutrientsMetaDataDto?,
    @Json(name = "FOLAC") val folac: NutrientsMetaDataDto?,
    @Json(name = "FOLDFE") val foldfe: NutrientsMetaDataDto?,
    @Json(name = "FOLFD") val folfd: NutrientsMetaDataDto?,
    @Json(name = "K") val k: NutrientsMetaDataDto?,
    @Json(name = "MG") val mg: NutrientsMetaDataDto?,
    @Json(name = "NA") val na: NutrientsMetaDataDto?,
    @Json(name = "NIA") val nia: NutrientsMetaDataDto?,
    @Json(name = "P") val p: NutrientsMetaDataDto?,
    @Json(name = "PROCNT") val procnt: NutrientsMetaDataDto?,
    @Json(name = "RIBF") val ribf: NutrientsMetaDataDto?,
    @Json(name = "SUGAR") val sugar: NutrientsMetaDataDto?,
    @Json(name = "SUGAR.added") val sugaradded: NutrientsMetaDataDto?,
    @Json(name = "THIA") val thia: NutrientsMetaDataDto?,
    @Json(name = "TOCPHA") val tocpaha: NutrientsMetaDataDto?,
    @Json(name = "VITA_RAE") val vitarAE: NutrientsMetaDataDto?,
    @Json(name = "VITB12") val vitb12: NutrientsMetaDataDto?,
    @Json(name = "VITB6A") val vitb6a: NutrientsMetaDataDto?,
    @Json(name = "VITC") val vitc: NutrientsMetaDataDto?,
    @Json(name = "VITD") val vitd: NutrientsMetaDataDto?,
    @Json(name = "VITK1") val vitk1: NutrientsMetaDataDto?,
    @Json(name = "WATER") val water: NutrientsMetaDataDto?,
    @Json(name = "ZN") val zn: NutrientsMetaDataDto?,
)
