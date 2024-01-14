package com.fitness.recipebuilder.viewmodel

import android.util.Log
import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamFoodSearchUseCase
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import util.RecipeFetchUseCase
import util.RecipeSearchUseCase
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeBuilderViewModel @Inject constructor(
    private val nutritionStateHolder: RecipeBuilderStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getNutritionalInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    private val edamamFoodSearchUseCase: EdamamFoodSearchUseCase,
) : IntentViewModel<BaseViewState<RecipeBuilderState>, RecipeBuilderEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: RecipeBuilderEvent) {
        when (event) {

            is RecipeBuilderEvent.AutoComplete -> onAutoComplete(event)

            is RecipeBuilderEvent.Search -> {
                val state = nutritionStateHolder.state().copy(food = emptyList())
                RecipeBuilderStateHolder.updateState(state)
                onFoodSearch(event)
            }

            is RecipeBuilderEvent.OpenRecipeBuilder -> {}

            is RecipeBuilderEvent.AddIngredient -> {}

            is RecipeBuilderEvent.RemoveIngredient -> {}

            is RecipeBuilderEvent.CloseRecipeBuilder -> {}

            is RecipeBuilderEvent.DateSelected -> {}

            is RecipeBuilderEvent.MealTypeSelected -> {}

            is RecipeBuilderEvent.SaveRecipe -> onSaveRecipe(event)
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() = safeLaunch {
        execute(getCurrentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            currentUserNutritionalInfo(it)
        }
    }

    private fun currentUserNutritionalInfo(id: String) = safeLaunch {
        val params = GetUserBasicNutritionInfoUseCase.Params(id = id)
        execute(getNutritionalInfoUseCase(params)) {
            onFetchContentFromCache(it)
        }
    }

    private fun onFetchContentFromCache(nutrition: UserBasicNutritionInfo) = safeLaunch {

    }

    private fun onAutoComplete(event: RecipeBuilderEvent.AutoComplete) = safeLaunch {

    }

    private fun onFoodSearch(event: RecipeBuilderEvent.Search) = safeLaunch {

    }

    private fun onSaveRecipe(event: RecipeBuilderEvent.SaveRecipe) = safeLaunch {

    }
}