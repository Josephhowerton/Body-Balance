package com.fitness.data.model.cache.edamam

import androidx.room.PrimaryKey

data class TotalDailyEntity(
    @PrimaryKey val id: String,
    val ca: NutrientsMetaDataEntity? = null,
    val chocdf: NutrientsMetaDataEntity? = null,
    val chole: NutrientsMetaDataEntity? = null,
    val enerckcal: NutrientsMetaDataEntity? = null,
    val fasat: NutrientsMetaDataEntity? = null,
    val fat: NutrientsMetaDataEntity? = null,
    val fe: NutrientsMetaDataEntity? = null,
    val fibtg: NutrientsMetaDataEntity? = null,
    val foldfe: NutrientsMetaDataEntity? = null,
    val k: NutrientsMetaDataEntity? = null,
    val mg: NutrientsMetaDataEntity? = null,
    val na: NutrientsMetaDataEntity? = null,
    val nia: NutrientsMetaDataEntity? = null,
    val p: NutrientsMetaDataEntity? = null,
    val procnt: NutrientsMetaDataEntity? = null,
    val ribf: NutrientsMetaDataEntity? = null,
    val thia: NutrientsMetaDataEntity? = null,
    val tocpaha: NutrientsMetaDataEntity? = null,
    val vitarae: NutrientsMetaDataEntity? = null,
    val vitb12: NutrientsMetaDataEntity? = null,
    val vitb6a: NutrientsMetaDataEntity? = null,
    val vitc: NutrientsMetaDataEntity? = null,
    val vitd: NutrientsMetaDataEntity? = null,
    val vitk1: NutrientsMetaDataEntity? = null,
    val zn: NutrientsMetaDataEntity? = null
)
