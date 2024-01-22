package com.fitness.recipebuilder.screens.search

import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.recipebuilder.util.RecipeBuilderStep

data class IngredientSearchState(
    val autoComplete: List<String> = emptyList(),
    val searchResults: List<Ingredient> = emptyList(),
    val ingredients: List<Ingredient> = emptyList(),
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING
)

sealed class IngredientSearchEvent{
    data class AutoComplete(val search: String): IngredientSearchEvent()
    data class Search(val search: String): IngredientSearchEvent()
    data class AddIngredient(val ingredient: Ingredient, val ingredients: List<Ingredient>): IngredientSearchEvent()
    data class RemoveIngredient(val ingredient: Ingredient, val ingredients: List<Ingredient>): IngredientSearchEvent()
    data class  CloseIngredientBuilder(val ingredients: List<Ingredient>): IngredientSearchEvent()
}