package com.fitness.recipebuilder.util

import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.domain.model.nutrition.Recipe
import enums.EMealType

object RecipeBuilderStateHolder {
    private var state: RecipeBuilderState = RecipeBuilderState()
    fun state() = state
    fun updateState(newState: RecipeBuilderState) {
        state = newState
    }

    fun clearState() {
        state = RecipeBuilderState()
    }
}
data class RecipeBuilderState(
    val searchResults: List<Ingredient> = emptyList(),
    val ingredients: MutableList<Ingredient> = mutableListOf(),
    val date: Long = 0L,
    val hour: Int = 0,
    val minute: Int = 0,
    val type: EMealType = EMealType.BREAKFAST,
    val name: String = "",
    val recipe: Recipe = Recipe()
)

enum class RecipeBuilderStep {
    PENDING,
    COMPLETE
}

