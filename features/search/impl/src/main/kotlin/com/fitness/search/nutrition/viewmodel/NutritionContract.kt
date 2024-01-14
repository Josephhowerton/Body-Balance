package com.fitness.search.nutrition.viewmodel

import com.fitness.search.nutrition.RecipeStep
import enums.EMealType

data class NutritionSearchState(
    val recipeToSave: Recipe? = null,
    val autoComplete: List<String> = emptyList(),
    val searchResults: List<Recipe> = emptyList(),
    val step: RecipeStep = RecipeStep.PENDING,
)

sealed class NutritionSearchEvent{
    data class AutoComplete(val search: String): NutritionSearchEvent()
    data class Search(val search: String): NutritionSearchEvent()
    data class RecipeSelected(val recipe: Recipe): NutritionSearchEvent()
    data class DateSelected(val date: Long): NutritionSearchEvent()
    data class MealTypeSelected(val type: EMealType): NutritionSearchEvent()
    data class SaveRecipe(val recipe: Recipe?): NutritionSearchEvent()

}