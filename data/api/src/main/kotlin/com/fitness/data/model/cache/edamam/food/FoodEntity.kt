package com.fitness.data.model.cache.edamam.food


/**
 * @param apiId Food Entity identifier
 * @param mId Meal Entity identifier that this Food Entity belongs to
 */
data class FoodEntity(
    val id: String,
    val apiId: String,
    val mId: String,
    val food: String,
    val label: String,
    val knownAs: String,
    val image: String,
    val brand: String,
    val category: String,
    val categoryLabel: String,
    val foodContentsLabel: String,
    val quantity: Int,
    val measure: String,
    val measureURI: String,
    val weight: Int,
    val retainedWeight: Int,
    val servingSizes: List<ServingSizeEntity>,
    val servingsPerContainer: Double,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val cautions: List<String>,
)