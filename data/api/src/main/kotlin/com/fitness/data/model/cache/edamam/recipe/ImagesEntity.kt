package com.fitness.data.model.cache.edamam.recipe

data class ImagesEntity(
    val Large: ImageMetaDataEntity,
    val Regular: ImageMetaDataEntity,
    val Small: ImageMetaDataEntity,
    val Thumbnail: ImageMetaDataEntity
)