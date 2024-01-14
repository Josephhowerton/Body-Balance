package com.fitness.domain.usecase.search

import com.fitness.data.repository.edamam.EdamamRecipeRepository
import com.fitness.domain.model.toDomainModel
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamFetchRecipesUseCaseImpl @Inject constructor(
    private val repository: EdamamRecipeRepository
) : EdamamRecipeSearchUseCase() {

    override suspend fun FlowCollector<DataState<List<Recipe>>>.execute(params: Params) {
        emit(DataState.Success(repository.getRecipes(params.toSearchQuery()).toDomainModel()))
    }
}