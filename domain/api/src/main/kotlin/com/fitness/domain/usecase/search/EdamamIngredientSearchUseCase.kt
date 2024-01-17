package com.fitness.domain.usecase.search

import com.fitness.domain.model.nutrition.Ingredient
import usecase.DataStateUseCase

abstract class EdamamIngredientSearchUseCase: DataStateUseCase<EdamamIngredientSearchUseCase.Params, List<Ingredient>>(){
    data class Params(
        val search: String? = null,
        val brand: String? = null,
        val health: String? = null,
        val calories: String? = null,
        val category: String? = null,
    )
}
