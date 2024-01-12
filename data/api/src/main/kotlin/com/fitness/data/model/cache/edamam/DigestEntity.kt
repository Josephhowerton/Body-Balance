package com.fitness.data.model.cache.edamam

data class DigestEntity(
    val daily: Double? = null,
    val hasRDI: Boolean? = null,
    val label: String? = null,
    val schemaOrgTag: String? = null,
    val sub: List<SubEntity>? = null,
    val tag: String? = null,
    val total: Double?  = null,
    val unit: String? = null,
)