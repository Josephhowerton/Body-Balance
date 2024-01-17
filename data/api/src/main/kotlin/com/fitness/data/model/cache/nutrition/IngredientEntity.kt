package com.fitness.data.model.cache.nutrition

data class IngredientEntity(
    val foodId: String? = null,
    val food: String? = null,
    val label: String? = null,
    val foodCategory: String? = null,
    val image: String? = null,
    val measure: String? = null,
    val quantity: Double? = null,
    val detailed: String? = null,
    val weight: Double? = null,
    val uri: String? = null,
    val knownAs: String? = null,
    val category: String? = null,
    val categoryLabel: String? = null,
    val calories: Int? = null,
    val cautions: List<String>? = null,
    val dietLabels: List<String>? = null,
    val healthLabels: List<String>? = null,
    val totalWeight: Double? = null,
    val qualifierLabel: String? = null,
    val qualifierUri: String? = null,
    val measureLabel: String? = null,
    val measureUri: String? = null,
    val measureWeight: Double? = null,
    val nutrients: Map<String, NutrientEntity>? = null
)
