package com.fitness.data.repository.edamam

import com.fitness.data.extensions.toIngredientEntity
import com.fitness.data.model.cache.nutrition.IngredientEntity
import com.fitness.data.model.network.edamam.food.FoodDataDto
import com.fitness.data.model.network.edamam.params.IngredientBrandParams
import com.fitness.data.model.network.edamam.params.IngredientSearchParams
import com.fitness.data.network.EdamamFoodService
import javax.inject.Inject

class EdamamFoodRepositoryImpl @Inject constructor(
    private val service: EdamamFoodService
) : EdamamFoodRepository {

    override suspend fun getAllFood(): List<IngredientEntity> =
        service.getAllFood().hints?.toIngredientEntity() ?: emptyList()

    override suspend fun getFoodByIngredient(params: IngredientSearchParams): List<IngredientEntity> =
        service.getFoodByIngredient(
            ingredient = params.ingredient,
            category = params.category,
            calories = params.calories,
            health = params.health
        ).hints?.toIngredientEntity() ?: emptyList()


    override suspend fun getFoodByBrand(params: IngredientBrandParams): List<IngredientEntity> =
        TODO("Not yet implemented")

    override suspend fun getFoodByUpc(upc: String): List<IngredientEntity> =
        TODO("Not yet implemented")

}

fun List<FoodDataDto>.toIngredientEntity(): List<IngredientEntity> = map {
    val ingredientEntities = mutableListOf<IngredientEntity>()
    it.measures?.forEach { measureDto ->
        measureDto?.qualified?.forEach { qualifiedDto ->
            qualifiedDto?.qualifiers?.forEach { qualifierDto ->
                it.food?.toIngredientEntity(measureDto, qualifierDto)?.let { ingredientEntity ->
                    ingredientEntities.add(ingredientEntity)
                }
            }
        }
    }
    return ingredientEntities
}
