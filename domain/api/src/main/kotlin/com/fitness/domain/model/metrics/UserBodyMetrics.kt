package com.fitness.domain.model.metrics

data class UserBodyMetrics (
    val userId: String,
    val bodyMassIndex: Double,
    val bodyFatPercentage: Double,
    val basalMetabolicRate: Double
)

