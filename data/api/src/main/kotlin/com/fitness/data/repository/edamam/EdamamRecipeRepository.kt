package com.fitness.data.repository.edamam

import com.fitness.data.model.cache.edamam.RecipeEntity
import com.fitness.data.model.network.edamam.params.RecipeSearchParams
import com.fitness.data.model.network.edamam.recipe.RecipeDto
import kotlinx.coroutines.flow.Flow

interface EdamamRecipeRepository {
    suspend fun search(params: RecipeSearchParams): List<RecipeDto>
    suspend fun getRecipes(params: RecipeSearchParams): List<RecipeEntity>

    suspend fun fetchRecipesByUri(
        type: String = "any",
        uri: List<String>,
        language: String? = null,
    ): List<RecipeEntity>

    suspend fun fetchRecipesById(
        type: String = "any",
        id: String,
        language: String? = null,
    ): List<RecipeEntity>
}