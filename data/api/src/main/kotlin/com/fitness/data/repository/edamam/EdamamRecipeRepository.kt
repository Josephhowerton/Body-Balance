package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.recipe.RecipeResponse

interface EdamamRecipeRepository {
    suspend fun getRecipes(
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
    ): RecipeResponse

    suspend fun getRecipesByUri(
        type: String,
        uri: ArrayList<String>,
        language: String?,
    ): RecipeResponse

    suspend fun getRecipesById(
        type: String,
        id: String,
        language: String?,
    ): RecipeResponse
}