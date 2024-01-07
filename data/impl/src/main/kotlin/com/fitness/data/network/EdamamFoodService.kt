package com.fitness.data.network

import com.fitness.data.model.network.edamam.food.FoodResponse
import network.nutrition.Nutrition
import network.nutrition.NutritionSource
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EdamamFoodService {
    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getAllFood(): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByIngredient(
        @Query(INGREDIENTS_PARAM) ingredient: String,
        @Query(BRAND_PARAM) brand: String?,
        @Query(NUTRITION_TYPE_PARAM) nutritionType: String?,
        @Query(HEALTH_PARAM) health: String?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(CATEGORY_PARAM) category: String?,
        @QueryMap nutrients: HashMap<String, String>?
    ): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByBrand(
        @Query(BRAND_PARAM) brand: String,
        @Query(INGREDIENTS_PARAM) ingredient: String?,
        @Query(NUTRITION_TYPE_PARAM) nutritionType: String?,
        @Query(HEALTH_PARAM) health: String?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(CATEGORY_PARAM) category: String?,
        @QueryMap nutrients: HashMap<String, String>?
    ): FoodResponse

    @Nutrition(NutritionSource.FOOD)
    @GET(PARSER_ENDPOINT)
    suspend fun getFoodByUpc(
        @Query(UPC_PARAM) upc: String,
        @Query(INGREDIENTS_PARAM) ingredient: String?,
        @Query(BRAND_PARAM) brand: String?,
        @Query(NUTRITION_TYPE_PARAM) nutritionType: String?,
        @Query(HEALTH_PARAM) health: String?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(CATEGORY_PARAM) category: String?,
        @QueryMap nutrients: HashMap<String, String>?
    ): FoodResponse

    companion object {
        const val PARSER_ENDPOINT = "parser"
        const val INGREDIENTS_PARAM = "ingr"
        const val BRAND_PARAM = "brand"
        const val UPC_PARAM = "upc"
        const val NUTRITION_TYPE_PARAM = " nutrition-type"
        const val HEALTH_PARAM = "health"
        const val CALORIES_PARAM = "calories"
        const val CATEGORY_PARAM = "category"
    }
}