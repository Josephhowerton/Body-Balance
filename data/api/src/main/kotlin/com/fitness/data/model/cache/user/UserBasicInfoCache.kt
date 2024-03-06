package com.fitness.data.model.cache.user

import enums.EGender
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement

data class UserBasicInfoCache(
    val userId: String = "",
    val age: Int = 0,
    val gender: EGender = EGender.MALE,
    val height: Double = 0.0,
    val weight: Double = 0.0,
    val waist: Double = 0.0,
    val lastUpdated: Long = 1L,
)