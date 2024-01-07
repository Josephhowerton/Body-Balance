package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.recipe.RecipeResponse
import com.fitness.data.network.EdamamRecipeService
import javax.inject.Inject

class EdamamRecipeRepositoryImpl @Inject constructor(
    private val service: EdamamRecipeService
): EdamamRecipeRepository {

    override suspend fun getRecipes(
        type: String,
        query: String,
        ingredients: String?,
        diet: ArrayList<String>?,
        health: ArrayList<String>?,
        cuisineType: ArrayList<String>?,
        mealType: ArrayList<String>?,
        dishType: ArrayList<String>?,
        calories: String?,
        time: String?,
        imageSize: ArrayList<String>?,
        glycemicIndex: String?,
        excluded: ArrayList<String>?,
        random: String?,
        co2EmissionsClass: String?,
        tag: ArrayList<String>?,
        language: String?,
    ): RecipeResponse = service.getRecipes(
        type,
        query,
        ingredients,
        diet,
        health,
        cuisineType,
        mealType,
        dishType,
        calories,
        time,
        imageSize,
        glycemicIndex,
        excluded,
        random,
        co2EmissionsClass,
        tag,
        language
    )

    override suspend fun getRecipesByUri(
        type: String,
        uri: ArrayList<String>,
        language: String?,
    ): RecipeResponse = service.getRecipesByUri(type, uri, language)

    override suspend fun getRecipesById(
        type: String,
        id: String,
        language: String?,
    ): RecipeResponse = service.getRecipesById(type, id, language)

}