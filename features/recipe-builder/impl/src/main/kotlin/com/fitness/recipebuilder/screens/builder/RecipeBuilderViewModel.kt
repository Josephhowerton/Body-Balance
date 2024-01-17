package com.fitness.recipebuilder.screens.builder

import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamFetchAllIngredientsUseCase
import com.fitness.domain.usecase.search.EdamamIngredientSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeBuilderViewModel @Inject constructor(
    private val stateHolder: RecipeBuilderStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getNutritionalInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val getAllIngredientsUseCase: EdamamFetchAllIngredientsUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    private val edamamFoodSearchUseCase: EdamamIngredientSearchUseCase,
) : IntentViewModel<BaseViewState<RecipeBuilderState>, RecipeBuilderEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: RecipeBuilderEvent) {
        when (event) {
            is RecipeBuilderEvent.OpenRecipeBuilder -> onOpenRecipeBuilder()

            is RecipeBuilderEvent.EditName -> onEditName(event)

            is RecipeBuilderEvent.AddIngredient -> onAddIngredient(event)

            is RecipeBuilderEvent.CloseRecipeBuilder -> onCloseRecipeBuilder()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() = setState(BaseViewState.Data(RecipeBuilderState()))

    private fun onOpenRecipeBuilder() = safeLaunch {
    }

    private fun onEditName(event: RecipeBuilderEvent.EditName) = safeLaunch {

    }
    private fun onAddIngredient(event: RecipeBuilderEvent.AddIngredient) = safeLaunch {

    }

    private fun onCloseRecipeBuilder() = safeLaunch {

    }
}