package com.fitness.data.network

import com.fitness.data.model.network.edamam.FoodResponse
import network.nutrition.Nutrition
import network.nutrition.NutritionSource
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamFoodService {
    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getAllFood(): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByIngredient(
        @Query(INGREDIENTS_PARAM) ingredient: String,
        @Query(HEALTH_PARAM) health: String?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(CATEGORY_PARAM) category: String?,
    ): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByBrand(
        @Query(BRAND_PARAM) brand: String,
        @Query(HEALTH_PARAM) health: String?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(CATEGORY_PARAM) category: String?,
    ): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByUpc(@Query(UPC_PARAM) upc: String): FoodResponse

    companion object {
        const val PARSER_ENDPOINT = "api/food-database/v2/parser"
        const val INGREDIENTS_PARAM = "ingr"
        const val BRAND_PARAM = "brand"
        const val UPC_PARAM = "upc"
        const val HEALTH_PARAM = "health"
        const val CALORIES_PARAM = "calories"
        const val CATEGORY_PARAM = "category"
    }
}