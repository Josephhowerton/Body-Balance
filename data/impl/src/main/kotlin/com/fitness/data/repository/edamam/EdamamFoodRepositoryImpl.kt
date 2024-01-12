package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.food.FoodData
import com.fitness.data.model.network.edamam.params.FoodSearchBrandParams
import com.fitness.data.model.network.edamam.params.FoodSearchIngredientParams
import com.fitness.data.network.EdamamFoodService
import javax.inject.Inject

class EdamamFoodRepositoryImpl @Inject constructor(
    private val service: EdamamFoodService
) : EdamamFoodRepository {

    override suspend fun getAllFood(): List<FoodData> {
        return service.getAllFood().hints?.run {
            mapNotNull {
                it
            }
        } ?: emptyList()
    }

    override suspend fun getFoodByIngredient(params: FoodSearchIngredientParams): List<FoodData> {
        return service.getFoodByIngredient(
            ingredient = params.ingredient,
            health = params.health,
            calories = params.calories,
            category = params.category
        ).hints?.run {
            return@run mapNotNull {
                return@mapNotNull it
            }
        } ?: emptyList()
    }

    override suspend fun getFoodByBrand(params: FoodSearchBrandParams): List<FoodData> {
        return service.getFoodByBrand(
            brand = params.brand,
            health = params.health,
            calories = params.calories,
            category = params.category
        ).hints?.run {
            mapNotNull {
                it
            }
        } ?: emptyList()
    }

    override suspend fun getFoodByUpc(upc: String): List<FoodData> = TODO()
}