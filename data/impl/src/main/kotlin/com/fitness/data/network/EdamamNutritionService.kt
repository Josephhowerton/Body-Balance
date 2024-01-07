package com.fitness.data.network

import com.fitness.data.model.network.edamam.nutrients.NutrientsResponse
import network.nutrition.Nutrition
import network.nutrition.NutritionSource
import retrofit2.http.POST

interface EdamamNutritionService {

    @Nutrition(NutritionSource.NUTRITION)
    @POST(NUTRIENTS_ENDPOINT)
    suspend fun getFoodNutrients(): NutrientsResponse

    companion object{
        const val NUTRIENTS_ENDPOINT = "nutrients"
        const val INGREDIENTS_PARAM = "ingredients"
        const val QUANTITY_PARAM = "quantity"
        const val MEASUREMENT_URI_PARAM = "measureURI"
        const val QUALIFIERS_PARAM = "qualifiers"
        const val FOOD_ID_PARAM = "foodId"
    }
}

