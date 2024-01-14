package com.fitness.domain.usecase.search

import usecase.DataStateUseCase

abstract class EdamamFoodSearchUseCase: DataStateUseCase<EdamamFoodSearchUseCase.Params, List<FoodData>>(){
    data class Params(
        val search: String? = null,
        val brand: String? = null,
        val health: String? = null,
        val calories: String? = null,
        val category: String? = null,
    )
}
