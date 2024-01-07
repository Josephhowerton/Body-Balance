package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.food.FoodResponse

interface EdamamFoodRepository {
    suspend fun getAllFood(): FoodResponse

    suspend fun getFoodByIngredient(
        ingredient: String,
        brand: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse

    suspend fun getFoodByBrand(
        brand: String,
        ingredient: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse

    suspend fun getFoodByUpc(
        upc: String,
        ingredient: String?,
        brand: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse
}