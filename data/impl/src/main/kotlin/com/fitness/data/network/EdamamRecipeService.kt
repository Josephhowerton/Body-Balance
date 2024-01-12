package com.fitness.data.network

import com.fitness.data.model.network.edamam.RecipeResponse
import network.nutrition.Nutrition
import network.nutrition.NutritionSource.RECIPE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EdamamRecipeService {

    @Nutrition(RECIPE)
    @GET(BASE_ENDPOINT)
    suspend fun getRecipes(
        @Query(TYPE_PARAM) type: String,
        @Query(QUERY_PARAM) query: String,
        @Query(INGREDIENTS_PARAM) ingredients: String?,
        @Query(DIET_PARAM) diet: List<String>?,
        @Query(HEALTH_PARAM) health: List<String>?,
        @Query(CUISINE_TYPE_PARAM) cuisineType: List<String>?,
        @Query(MEAL_TYPE_PARAM) mealType: List<String>?,
        @Query(DISH_TYPE_PARAM) dishType: List<String>?,
        @Query(CALORIES_PARAM) calories: String?,
        @Query(TIME_PARAM) time: String?,
        @Query(IMAGE_SIZE_PARAM) imageSize: List<String>?,
        @Query(GLYCEMIC_INDEX_PARAM) glycemicIndex: String?,
        @Query(EXCLUDED_PARAM) excluded: List<String>?,
        @Query(RANDOM_PARAM) random: String?,
        @Query(C02_EMISSION_PARAM) co2EmissionsClass: String?,
        @Query(TAG_PARAM) tag: List<String>?,
        @Query(ACCEPT_LANGUAGE_PARAM) language: String?,
    ): RecipeResponse

    @Nutrition(RECIPE)
    @GET("$BASE_ENDPOINT/$URI_ENDPOINT")
    suspend fun getRecipesByUri(
        @Query(TYPE_PARAM) type: String,
        @Query(URI_PARAM) uri: List<String>,
        @Query(ACCEPT_LANGUAGE_PARAM) language: String?,
    ): RecipeResponse

    @Nutrition(RECIPE)
    @GET("$BASE_ENDPOINT/{$ID_ENDPOINT}")
    suspend fun getRecipesById(
        @Query(TYPE_PARAM) type: String,
        @Path(ID_ENDPOINT) id: String,
        @Query(ACCEPT_LANGUAGE_PARAM) language: String?,
    ): RecipeResponse

    companion object {
        const val BASE_ENDPOINT = "api/recipes/v2"
        const val URI_ENDPOINT = "by-uri"
        const val ID_ENDPOINT = "id"
        const val URI_PARAM = "uri"
        const val TYPE_PARAM = "type"
        const val QUERY_PARAM = "q"
        const val INGREDIENTS_PARAM = "ingr"
        const val DIET_PARAM = "diet"
        const val HEALTH_PARAM = "health"
        const val CUISINE_TYPE_PARAM = "cuisineType"
        const val MEAL_TYPE_PARAM = "mealType"
        const val DISH_TYPE_PARAM = "dishType"
        const val CALORIES_PARAM = "calories"
        const val TIME_PARAM = "time"
        const val IMAGE_SIZE_PARAM = "imageSize"
        const val GLYCEMIC_INDEX_PARAM = "glycemicIndex"
        const val EXCLUDED_PARAM = "excluded"
        const val RANDOM_PARAM = "random"
        const val C02_EMISSION_PARAM = "co2EmissionsClass"
        const val TAG_PARAM = "tag"
        const val ACCEPT_LANGUAGE_PARAM = "Accept-Language"
    }
}