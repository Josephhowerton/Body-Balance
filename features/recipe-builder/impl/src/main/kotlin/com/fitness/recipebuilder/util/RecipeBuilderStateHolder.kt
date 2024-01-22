package com.fitness.recipebuilder.util

import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.domain.model.nutrition.Nutrition
import com.fitness.domain.model.nutrition.Recipe
import enums.EMealType

object RecipeBuilderStateHolder {
    private var state: BuilderState = BuilderState()
    fun state() = state
    fun updateState(newState: BuilderState) {
        state = newState
    }

    fun clearState() {
        state = BuilderState()
    }
}
data class BuilderState(
    val name: String? = null,
    val date: Long? = null,
    val hour: Int? = null,
    val minute: Int? = null,
    val type: EMealType? = null,
    val recordId: String? = null,
    val recipe: Recipe? = null,
    val myRecipes: List<Nutrition> = mutableListOf(),
    val ingredients: List<Ingredient> = mutableListOf(),
    val instructions: List<String> = mutableListOf(),
    val searchResults: List<Ingredient> = emptyList(),
)

enum class RecipeSelectionStep {
    PENDING,
    NAME,
    CREATE,
    MODIFY,
}

enum class RecipeBuilderStep {
    PENDING,
    COMPLETE
}

