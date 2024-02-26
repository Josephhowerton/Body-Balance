package com.fitness.data.model.cache.metrics

data class UserBodyMetricsCache (
    val userId: String,
    val bodyMassIndex: Double,
    val bodyFatPercentage: Double,
    val basalMetabolicRate: Double
)

