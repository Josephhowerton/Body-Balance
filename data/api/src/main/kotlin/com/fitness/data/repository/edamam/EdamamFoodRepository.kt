package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.food.FoodData
import com.fitness.data.model.network.edamam.params.FoodSearchBrandParams
import com.fitness.data.model.network.edamam.params.FoodSearchIngredientParams

interface EdamamFoodRepository {
    suspend fun getAllFood(): List<FoodData>

    suspend fun getFoodByIngredient(params: FoodSearchIngredientParams): List<FoodData>

    suspend fun getFoodByBrand(params: FoodSearchBrandParams): List<FoodData>

    suspend fun getFoodByUpc(upc: String): List<FoodData>
}