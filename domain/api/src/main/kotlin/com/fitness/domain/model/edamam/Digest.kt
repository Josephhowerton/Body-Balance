package com.fitness.domain.model.edamam

data class Digest(
    val daily: Double? = null,
    val hasRDI: Boolean? = null,
    val label: String? = null,
    val schemaOrgTag: String? = null,
    val sub: List<Sub>? = null,
    val tag: String? = null,
    val total: Double?  = null,
    val unit: String? = null
)