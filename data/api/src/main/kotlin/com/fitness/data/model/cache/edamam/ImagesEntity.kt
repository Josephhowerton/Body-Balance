package com.fitness.data.model.cache.edamam



data class ImagesEntity(
    val large: ImageMetaDataEntity? = null,
    val regular: ImageMetaDataEntity? = null,
    val small: ImageMetaDataEntity? = null,
    val thumbnail: ImageMetaDataEntity? = null
)