package com.fitness.analytics

import enums.EGender
import enums.EPhysicalActivityLevel

fun calculateBMR(gender: EGender, weightKg: Double, heightCm: Double, waistCm: Double, ageYears: Int): Double {
    val bmr: Double = when (gender) {
        EGender.MALE -> (10 * weightKg) + (6.25 * heightCm) - (5 * ageYears) + 5
        EGender.FEMALE -> (10 * weightKg) + (6.25 * heightCm) - (5 * ageYears) - 161
    }

    val waistFactor = when (gender) {
        EGender.MALE -> 1.0
        EGender.FEMALE -> 0.8
    }

    return bmr * (waistCm / heightCm) * waistFactor
}