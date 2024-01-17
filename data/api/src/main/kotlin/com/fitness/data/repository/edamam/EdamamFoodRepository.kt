package com.fitness.data.repository.edamam

import com.fitness.data.model.cache.nutrition.IngredientEntity
import com.fitness.data.model.network.edamam.food.FoodDto
import com.fitness.data.model.network.edamam.params.IngredientBrandParams
import com.fitness.data.model.network.edamam.params.IngredientSearchParams
import com.fitness.data.model.network.edamam.recipe.IngredientDto

interface EdamamFoodRepository {
    suspend fun getAllFood(): List<IngredientEntity>

    suspend fun getFoodByIngredient(params: IngredientSearchParams): List<IngredientEntity>

    suspend fun getFoodByBrand(params: IngredientBrandParams): List<IngredientEntity>

    suspend fun getFoodByUpc(upc: String): List<IngredientEntity>
}