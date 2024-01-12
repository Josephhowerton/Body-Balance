package com.fitness.search.nutrition.viewmodel

import android.util.Log
import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamFoodSearchUseCase
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.search.nutrition.NutritionStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import util.RecipeFetchUseCase
import util.RecipeSearchUseCase
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionSearchViewModel @Inject constructor(
    private val nutritionStateHolder: NutritionStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getNutritionalInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    private val edamamFoodSearchUseCase: EdamamFoodSearchUseCase,
    @RecipeSearchUseCase private val edamamRecipeSearchUseCase: EdamamRecipeSearchUseCase,
    @RecipeFetchUseCase private val edamamFetchRecipesUseCase: EdamamRecipeSearchUseCase
) : IntentViewModel<BaseViewState<NutritionSearchState>, NutritionSearchEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: NutritionSearchEvent) {
        when (event) {

            is NutritionSearchEvent.AutoComplete -> onAutoComplete(event)

            is NutritionSearchEvent.SaveRecipe -> onSaveRecipe(event)

            is NutritionSearchEvent.SaveFood -> onSaveFood(event)

            is NutritionSearchEvent.Search -> {
                val state = nutritionStateHolder.getState().copy(recipes = emptyList(), food = emptyList())
                nutritionStateHolder.updateState(state)
                onFoodSearch(event)
                onRecipeSearch(event)
            }

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
        val param = EdamamRecipeSearchUseCase.Params(search = "", cuisineType = nutrition.cuisineTypeApi)
        execute(edamamFetchRecipesUseCase(param)) {
            val state = nutritionStateHolder.getState().copy(recipes = it)
            nutritionStateHolder.updateState(state)
            setState(
                BaseViewState.Data(
                    NutritionSearchState(
                        recipes = state.recipes,
                        food = state.food
                    )
                )
            )
        }
    }

    private fun onAutoComplete(event: NutritionSearchEvent.AutoComplete) = safeLaunch {
        val param = EdamamAutoCompleteUseCase.Params(event.search)
        call(edamamAutoCompleteUseCase(param)) {
            val state = nutritionStateHolder.getState()
            setState(
                BaseViewState.Data(
                    NutritionSearchState(
                        autoComplete = it,
                        recipes = state.recipes,
                        food = state.food
                    )
                )
            )
        }
    }

    private fun onFoodSearch(event: NutritionSearchEvent.Search) = safeLaunch {
        val param = EdamamFoodSearchUseCase.Params(search = event.search)
        execute(edamamFoodSearchUseCase(param)) {
            Log.e("onFoodSearch", "results")
            val state = nutritionStateHolder.getState().copy(food = it)
            setState(
                BaseViewState.Data(
                    NutritionSearchState(
                        recipes = state.recipes,
                        food = state.food
                    )
                )
            )
        }
    }

    private fun onRecipeSearch(event: NutritionSearchEvent.Search) = safeLaunch {
        val param = EdamamRecipeSearchUseCase.Params(event.search)
        execute(edamamRecipeSearchUseCase(param)) {
            Log.e("onRecipeSearch", "results")
            val state = nutritionStateHolder.getState().copy(recipes = it)
            nutritionStateHolder.updateState(state)
            setState(
                BaseViewState.Data(
                    NutritionSearchState(
                        recipes = it,
                        food = state.food
                    )
                )
            )
        }
    }

    private fun onSaveFood(event: NutritionSearchEvent.SaveFood) = safeLaunch {

    }

    private fun onSaveRecipe(event: NutritionSearchEvent.SaveRecipe) = safeLaunch {

    }
}