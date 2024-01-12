package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Food(
    val brand: String? = null,
    val category: String? = null,
    val categoryLabel: String? = null,
    val foodContentsLabel: String? = null,
    val foodId: String? = null,
    val image: String? = null,
    val knownAs: String? = null,
    val label: String? = null,
    val nutrients: Nutrients? = null,
    val servingSizes: List<ServingSize?>? = null,
    val servingsPerContainer: Double? = null
)