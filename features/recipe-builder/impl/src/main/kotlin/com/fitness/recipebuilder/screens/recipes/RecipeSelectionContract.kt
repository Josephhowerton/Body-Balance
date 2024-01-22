package com.fitness.recipebuilder.screens.recipes

import com.fitness.domain.model.nutrition.Nutrition
import com.fitness.recipebuilder.util.RecipeSelectionStep

data class RecipeSelectionState(
    val myRecipes: List<Nutrition> = emptyList(),
    val step: RecipeSelectionStep = RecipeSelectionStep.PENDING
)

sealed class RecipeSelectionEvent{
    data class RecipeSelected(val nutrition: Nutrition): RecipeSelectionEvent()
    object CancelCreate: RecipeSelectionEvent()
    object CreateNewRecipe: RecipeSelectionEvent()
    data class GiveName(val name: String): RecipeSelectionEvent()
}