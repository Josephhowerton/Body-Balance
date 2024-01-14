package com.fitness.data.cache

import com.fitness.data.model.cache.nutrition.RecipeEntity
import com.google.gson.Gson

const val RecipeFreshnessThreshold = 86_400_000
fun serializeRecipeEntity(recipe: RecipeEntity): String {
    val gson = Gson()
    return gson.toJson(recipe)
}


fun deserializeRecipeEntity(recipe: String): RecipeEntity {
    val gson = Gson()
    return gson.fromJson(recipe, RecipeEntity::class.java)
}