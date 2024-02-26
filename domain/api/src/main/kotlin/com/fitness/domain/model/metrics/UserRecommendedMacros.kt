package com.fitness.domain.model.metrics

data class UserRecommendedMacros (
    val userId: String,
    val fat: Double,
    val protein: Double,
    val calories: Double,
    val totalDailyEnergyExpenditure: Double,
    val carbohydrates: Double,
    val fiber: Double
)