package com.fitness.data.model.cache.user

import enums.EGender
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement

data class UserBasicInfoCache(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double,
    val waist: Double,
    val lastUpdated: Long,
)