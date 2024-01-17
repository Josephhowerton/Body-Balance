package com.fitness.recipebuilder.screens.builder

import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.recipebuilder.util.RecipeBuilderStep

data class RecipeBuilderState(
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING
)

sealed class RecipeBuilderEvent{
    object OpenRecipeBuilder: RecipeBuilderEvent()
    data class AddIngredient(val ingredient: Ingredient): RecipeBuilderEvent()
    data class EditName(val name: String): RecipeBuilderEvent()
    object CloseRecipeBuilder: RecipeBuilderEvent()
}