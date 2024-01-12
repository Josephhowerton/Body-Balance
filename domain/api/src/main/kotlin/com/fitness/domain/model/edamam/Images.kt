package com.fitness.domain.model.edamam


data class Images(
    val large: ImageMetaData? = null,
    val regular: ImageMetaData? = null,
    val small: ImageMetaData? = null,
    val thumbnail: ImageMetaData? = null
)