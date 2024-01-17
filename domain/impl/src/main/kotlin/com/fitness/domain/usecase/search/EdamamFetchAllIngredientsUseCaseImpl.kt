package com.fitness.domain.usecase.search

import com.fitness.data.repository.edamam.EdamamFoodRepository
import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.domain.model.nutrition.toIngredient
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamFetchAllIngredientsUseCaseImpl @Inject constructor(private val repository: EdamamFoodRepository): EdamamFetchAllIngredientsUseCase(){

    override suspend fun FlowCollector<DataState<List<Ingredient>>>.execute(params: EdamamIngredientSearchUseCase.Params) {
        val results = repository.getAllFood().map { it.toIngredient() }
        emit(DataState.Success(results))
    }
}