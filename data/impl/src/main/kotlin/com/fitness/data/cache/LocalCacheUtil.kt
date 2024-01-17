package com.fitness.data.cache

import com.fitness.data.model.network.edamam.recipe.RecipeDto
import com.google.gson.Gson

const val RecipeFreshnessThreshold = 86_400_000
fun serializeRecipeEntity(recipe: RecipeDto): String {
    val gson = Gson()
    return gson.toJson(recipe)
}


fun deserializeRecipeEntity(recipe: String): RecipeDto {
    val gson = Gson()
    return gson.fromJson(recipe, RecipeDto::class.java)
}