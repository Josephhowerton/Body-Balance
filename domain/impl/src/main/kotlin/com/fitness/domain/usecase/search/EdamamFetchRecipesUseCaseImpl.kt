package com.fitness.domain.usecase.search

import com.fitness.data.repository.edamam.EdamamRecipeRepository
import com.fitness.domain.model.nutrition.Recipe
import com.fitness.domain.model.nutrition.toRecipeFromRecipeDto
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamFetchRecipesUseCaseImpl @Inject constructor(
    private val repository: EdamamRecipeRepository
) : EdamamRecipeSearchUseCase() {

    override suspend fun FlowCollector<DataState<List<Recipe>>>.execute(params: Params) {
        val result = repository.fetchRecipes(params.toSearchQuery()).map { it.toRecipeFromRecipeDto() }
        emit(DataState.Success(result))
    }
}