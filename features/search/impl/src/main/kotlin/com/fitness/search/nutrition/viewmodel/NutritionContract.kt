package com.fitness.search.nutrition.viewmodel

import com.fitness.data.model.network.edamam.food.Food
import com.fitness.data.model.network.edamam.food.FoodData
import com.fitness.domain.model.edamam.Recipe

data class NutritionSearchState(
    val autoComplete: List<String> = emptyList(),
    val recipes: List<Recipe> = emptyList(),
    val food: List<FoodData> = emptyList()
)

sealed class NutritionSearchEvent{
    data class AutoComplete(val search: String): NutritionSearchEvent()
    data class Search(val search: String): NutritionSearchEvent()
    data class SaveRecipe(val recipe: Recipe): NutritionSearchEvent()
    data class SaveFood(val foodData: FoodData): NutritionSearchEvent()
}