package com.fitness.domain.model.user

import enums.EGender
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement

data class UserBasicInfo(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double,
    val waist: Double
)