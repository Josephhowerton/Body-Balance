package com.fitness.search.screens.recipe

import com.fitness.domain.model.nutrition.Recipe
import com.fitness.search.SearchRecipeStep

data class RecipeSearchState(
    val autoComplete: List<String> = emptyList(),
    val searchResults: List<Recipe> = emptyList(),
    val step: SearchRecipeStep = SearchRecipeStep.PENDING,
)

sealed class RecipeSearchEvent{
    data class AutoComplete(val search: String): RecipeSearchEvent()
    data class Search(val search: String): RecipeSearchEvent()
    data class RecipeSelected(val recipe: Recipe): RecipeSearchEvent()

}