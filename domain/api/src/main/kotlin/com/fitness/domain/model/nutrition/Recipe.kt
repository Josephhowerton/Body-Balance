package com.fitness.domain.model.nutrition

import cache.generateUniqueId

data class Recipe(
    val calories: Double? = null,
    val large: String? = null,
    val regular: String? = null,
    val small: String? = null,
    val thumbnail: String? = null,
    val label: String? = null,
    val mealType: List<String>? = null,
    val shareAs: String? = null,
    val source: String? = null,
    val tags: List<String>? = null,
    val totalTime: Double? = null,
    val totalWeight: Double? = null,
    val recipeUri: String? = null,
    val recipeUrl: String? = null,
    val yield: Double? = null,
    val cautions: List<String>? = null,
    val cuisineType: List<String>? = null,
    val dietLabels: List<String>? = null,
    val dishType: List<String>? = null,
    val healthLabels: List<String>? = null,
    val standardImage: String? = null,
    val ingredientLines: List<String>? = null,
    val instructionLines: List<String>? = null,
    val ingredients: List<Ingredient>? = null,
    val nutrients: Map<String, Nutrient>? = null,
    val recipeId: String = generateRecipeId("$recipeUri-$recipeUrl", ingredients)
)

fun generateRecipeId(input: String, ingredients: List<Ingredient>?): String {
    var foodId = ""
    ingredients?.forEach {
        foodId += it.foodId
    }

    return generateUniqueId("$input-$foodId")
}