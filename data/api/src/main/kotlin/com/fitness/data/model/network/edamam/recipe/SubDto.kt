package com.fitness.data.model.network.edamam.recipe


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubDto(
    val daily: Double?,
    val hasRDI: Boolean?,
    val label: String?,
    val schemaOrgTag: String?,
    val tag: String?,
    val total: Double?,
    val unit: String?
)