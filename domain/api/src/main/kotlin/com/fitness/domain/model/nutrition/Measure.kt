package com.fitness.domain.model.nutrition

data class Measure(
    val label: String? = null,
    val qualified: List<Qualified>? = null,
    val uri: String? = null,
    val weight: Double? = null
)