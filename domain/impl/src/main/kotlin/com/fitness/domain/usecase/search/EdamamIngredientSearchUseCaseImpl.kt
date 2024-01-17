package com.fitness.domain.usecase.search


import com.fitness.data.model.network.edamam.params.IngredientBrandParams
import com.fitness.data.model.network.edamam.params.IngredientSearchParams
import com.fitness.data.repository.edamam.EdamamFoodRepository
import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.domain.model.nutrition.toIngredient
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EdamamIngredientSearchUseCaseImpl @Inject constructor(private val repository: EdamamFoodRepository) :
    EdamamIngredientSearchUseCase() {

    override suspend fun FlowCollector<DataState<List<Ingredient>>>.execute(params: Params) {
        params.search?.let { query ->
            val searchParams = IngredientSearchParams(
                ingredient = query,
                health = params.health,
                calories = params.calories,
                category = params.category
            )
            val food = repository.getFoodByIngredient(searchParams).map { it.toIngredient() }
            emit(DataState.Success(food))
        } ?: params.brand?.let { brand ->
            val searchParams = IngredientBrandParams(
                brand = brand,
                health = params.health,
                calories = params.calories,
                category = params.category
            )
            val food = repository.getFoodByBrand(searchParams).map { it.toIngredient() }
            emit(DataState.Success(food))
        } ?: throw Exception()

    }
}
