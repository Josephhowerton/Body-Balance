package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.food.FoodResponse
import com.fitness.data.network.EdamamFoodService
import javax.inject.Inject

class EdamamFoodRepositoryImpl @Inject constructor(
    private val service: EdamamFoodService
): EdamamFoodRepository {

    override suspend fun getAllFood(): FoodResponse = service.getAllFood()

    override suspend fun getFoodByIngredient(
        ingredient: String,
        brand: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse = service.getFoodByIngredient(ingredient, brand, nutritionType, health, calories, category, nutrients)

    override suspend fun getFoodByBrand(
        brand: String,
        ingredient: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse = service.getFoodByBrand(brand, ingredient, nutritionType, health, calories, category, nutrients)

    override suspend fun getFoodByUpc(
        upc: String,
        ingredient: String?,
        brand: String?,
        nutritionType: String?,
        health: String?,
        calories: String?,
        category: String?,
        nutrients: HashMap<String, String>?
    ): FoodResponse = service.getFoodByUpc(upc, ingredient, brand, nutritionType, health, calories, category, nutrients)
}