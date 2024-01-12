package com.fitness.data.model.network.edamam.params

data class RecipeSearchParams(
    val type: String = "any",
    val query: String,
    val ingredients: String? = null,
    val diet: List<String>? = null,
    val health: List<String>? = null,
    val cuisineType: List<String>? = null,
    val mealType: List<String>? = null,
    val dishType: List<String>? = null,
    val calories: String? = null,
    val time: String? = null,
    val imageSize: List<String>? = null,
    val glycemicIndex: String? = null,
    val excluded: List<String>? = null,
    val random: String? = null,
    val co2EmissionsClass: String? = null,
    val tag: List<String>? = null,
    val language: String? = null
)
