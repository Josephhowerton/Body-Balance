package com.fitness.search

import com.fitness.domain.model.nutrition.Recipe
import enums.EMealType

object RecipeSearchStateHolder {
    private var state: SearchRecipeState = SearchRecipeState()
    fun state() = state
    fun updateState(newState: SearchRecipeState) {
        state = newState
    }

    fun clearState() {
        state = SearchRecipeState()
    }
}
data class SearchRecipeState(
    val recipesSearchResults: List<Recipe> = emptyList(),
    val recipe: Recipe = Recipe(),
    val date: Long = 0L,
    val hour: Int = 0,
    val minute: Int = 0,
    val type: EMealType = EMealType.BREAKFAST,
)

enum class SearchRecipeStep {
    PENDING,
    COMPLETE
}

