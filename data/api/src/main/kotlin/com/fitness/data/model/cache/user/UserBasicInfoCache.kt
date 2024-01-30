package com.fitness.data.model.cache.user

import enums.EGender
import enums.ELengthUnit
import enums.EMassUnit

data class UserBasicInfoCache(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double,
    val waist: Double,
    val bmi: Double,
    val heightUnit: ELengthUnit = ELengthUnit.METER,
    val weightUnit: EMassUnit = EMassUnit.GRAM,
    val waistUnit: ELengthUnit = ELengthUnit.METER,
    val lastUpdated: Long,
)