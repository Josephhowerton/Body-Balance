package com.fitness.recipebuilder.viewmodel

import enums.EMealType

data class RecipeBuilderState(
    val autoComplete: List<String> = emptyList(),
    val foodSearchResults: List<FoodData> = emptyList(),
    val ingredients: List<Food> = emptyList(),
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING
)

sealed class RecipeBuilderEvent{
    data class AutoComplete(val search: String): RecipeBuilderEvent()
    data class Search(val search: String): RecipeBuilderEvent()
    data class OpenRecipeBuilder(val foodData: Food): RecipeBuilderEvent()
    data class AddIngredient(val foodData: Food): RecipeBuilderEvent()
    data class RemoveIngredient(val foodData: Food): RecipeBuilderEvent()
    object CloseRecipeBuilder: RecipeBuilderEvent()
    data class DateSelected(val date: Long): RecipeBuilderEvent()
    data class MealTypeSelected(val type: EMealType): RecipeBuilderEvent()
    data class SaveRecipe(val ingredients: List<Food>): RecipeBuilderEvent()
}