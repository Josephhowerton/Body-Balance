package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.params.IngredientBrandParams
import com.fitness.data.model.network.edamam.params.FoodSearchIngredientParams

interface EdamamFoodRepository {
    suspend fun getAllFood(): List<FoodData>

    suspend fun getFoodByIngredient(params: FoodSearchIngredientParams): List<FoodData>

    suspend fun getFoodByBrand(params: IngredientBrandParams): List<FoodData>

    suspend fun getFoodByUpc(upc: String): List<FoodData>
}