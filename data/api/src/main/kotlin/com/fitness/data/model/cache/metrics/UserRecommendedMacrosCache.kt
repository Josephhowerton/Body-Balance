package com.fitness.data.model.cache.metrics

data class UserRecommendedMacrosCache (
    val userId: String,
    val fat: Double,
    val protein: Double,
    val calories: Double,
    val totalDailyEnergyExpenditure: Double,
    val carbohydrates: Double,
    val fiber: Double
)