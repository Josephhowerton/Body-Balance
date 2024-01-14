package com.fitness.domain.usecase.search

import com.fitness.data.repository.edamam.EdamamRecipeRepository
import com.fitness.domain.model.toDomainModel
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamRecipeSearchUseCaseImpl @Inject constructor(
    private val repository: EdamamRecipeRepository
) : EdamamRecipeSearchUseCase() {

    override suspend fun FlowCollector<DataState<List<Recipe>>>.execute(params: Params) {
        val result = repository.search(params.toSearchQuery())

        emit(DataState.Success(result.toDomainModel()))
    }
}