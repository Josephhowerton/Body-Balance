package com.fitness.domain.usecase.search


import com.fitness.data.model.network.edamam.params.RecipeSearchParams
import com.fitness.domain.model.edamam.Recipe
import usecase.DataStateUseCase

abstract class EdamamRecipeSearchUseCase : DataStateUseCase<EdamamRecipeSearchUseCase.Params, List<Recipe>>() {
    data class Params(
        val search: String,
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
        val language: String? = null,
    )

    protected fun Params.toSearchQuery(): RecipeSearchParams =
        RecipeSearchParams(
            query = search,
            ingredients = ingredients,
            diet = diet,
            health = health,
            cuisineType = cuisineType,
            mealType = mealType,
            dishType = dishType,
            calories = calories,
            time = time,
            imageSize = imageSize,
            glycemicIndex = glycemicIndex,
            excluded = excluded,
            random = random,
            co2EmissionsClass = co2EmissionsClass,
            tag = tag,
            language = language
        )

}