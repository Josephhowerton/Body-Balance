package com.fitness.domain.usecase.search

import com.fitness.data.model.network.edamam.params.IngredientBrandParams
import com.fitness.data.model.network.edamam.params.FoodSearchIngredientParams
import com.fitness.data.repository.edamam.EdamamFoodRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamFoodSearchUseCaseImpl @Inject constructor(private val repository: EdamamFoodRepository) :
    EdamamFoodSearchUseCase() {

    override suspend fun FlowCollector<DataState<List<FoodData>>>.execute(params: Params) {
        params.search?.let {
            val searchParams = FoodSearchIngredientParams(
                ingredient = it,
                health = params.health,
                calories = params.calories,
                category = params.category
            )
            val food = repository.getFoodByIngredient(searchParams)
            emit(DataState.Success(food))
        } ?: params.brand?.let {
            val searchParams = IngredientBrandParams(
                brand = it,
                health = params.health,
                calories = params.calories,
                category = params.category
            )
            emit(DataState.Success(repository.getFoodByBrand(searchParams)))
        } ?: throw Exception()

    }
}
