package com.fitness.domain.usecase.search

import com.fitness.domain.model.nutrition.Ingredient
import usecase.DataStateUseCase

abstract class EdamamFetchAllIngredientsUseCase: DataStateUseCase<EdamamFetchAllIngredientsUseCase.Params, List<Ingredient>>(){
    object Params
}