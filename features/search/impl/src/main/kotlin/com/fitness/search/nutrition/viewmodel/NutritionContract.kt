package com.fitness.search.nutrition.viewmodel

data class NutritionSearchState(val result: List<String>)

sealed class NutritionSearchEvent{
    data class Search(val search: String): NutritionSearchEvent()
}