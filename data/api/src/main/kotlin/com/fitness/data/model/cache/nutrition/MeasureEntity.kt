package com.fitness.data.model.cache.nutrition

data class MeasureEntity(
    val label: String? = null,
    val qualified: List<QualifiedEntity>? = null,
    val uri: String? = null,
    val weight: Double? = null
)