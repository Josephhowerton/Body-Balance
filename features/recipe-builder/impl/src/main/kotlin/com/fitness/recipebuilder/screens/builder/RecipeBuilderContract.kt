package com.fitness.recipebuilder.screens.builder

import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.recipebuilder.util.RecipeBuilderStep
import enums.EMealType

data class RecipeBuilderState(
    val name: String,
    val date: Long? = null,
    val hour: Int? = null,
    val minute: Int? = null,
    val type: EMealType? = null,
    val ingredients: List<Ingredient> = mutableListOf(),
    val instructions: List<String> = mutableListOf(),
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING
)

sealed class RecipeBuilderEvent {
    object GenerateInstructions : RecipeBuilderEvent()
    data class EditName(val name: String) : RecipeBuilderEvent()
    data class EditDate(val date: String) : RecipeBuilderEvent()
    data class EditType(val type: EMealType) : RecipeBuilderEvent()
    data class EditInstructions(val name: String) : RecipeBuilderEvent()
    data class RemoveIngredient(val ingredient: Ingredient) : RecipeBuilderEvent()
    object SaveNutritionRecord : RecipeBuilderEvent()
}