package com.fitness.recipebuilder.screens.search

import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamFetchAllIngredientsUseCase
import com.fitness.domain.usecase.search.EdamamIngredientSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import com.fitness.recipebuilder.util.RecipeBuilderStep
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientSearchViewModel @Inject constructor(
    private val stateHolder: RecipeBuilderStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getNutritionalInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val getAllIngredientsUseCase: EdamamFetchAllIngredientsUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    private val edamamFoodSearchUseCase: EdamamIngredientSearchUseCase,
) : IntentViewModel<BaseViewState<IngredientSearchState>, IngredientSearchEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: IngredientSearchEvent) {
        when (event) {
            is IngredientSearchEvent.AutoComplete -> onAutoComplete(event)

            is IngredientSearchEvent.Search -> onIngredientSearch(event)

            is IngredientSearchEvent.AddIngredient -> onAddIngredient(event)

            is IngredientSearchEvent.RemoveIngredient -> onRemoveIngredient(event)

            is IngredientSearchEvent.CloseIngredientBuilder -> onCloseRecipeBuilder(event)
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
        val state = RecipeBuilderStateHolder.state().copy(ingredients = mutableListOf())
        RecipeBuilderStateHolder.updateState(state)
        val params = GetUserBasicNutritionInfoUseCase.Params(id = id)
        execute(getNutritionalInfoUseCase(params)) {
            onFetchContentFromCacheIfExist(it)
        }
    }

    private fun onFetchContentFromCacheIfExist(nutrition: UserBasicNutritionInfo) = safeLaunch {
        val param = EdamamFetchAllIngredientsUseCase.Params
        execute(getAllIngredientsUseCase(param)) {
            val state = stateHolder.state().copy(searchResults = it)
            stateHolder.updateState(state)
            setState(BaseViewState.Data(IngredientSearchState(searchResults = state.searchResults)))
        }
    }

    private fun onAutoComplete(event: IngredientSearchEvent.AutoComplete) = safeLaunch {
        val param = EdamamAutoCompleteUseCase.Params(event.search)
        call(edamamAutoCompleteUseCase(param)) {
            val state = stateHolder.state()
            setState(
                BaseViewState.Data(
                    IngredientSearchState(
                        autoComplete = it,
                        searchResults = state.searchResults
                    )
                )
            )
        }
    }

    private fun onIngredientSearch(event: IngredientSearchEvent.Search) = safeLaunch {
        stateHolder.updateState(stateHolder.state().copy(searchResults = emptyList()))
        val param = EdamamIngredientSearchUseCase.Params(event.search)
        execute(edamamFoodSearchUseCase(param)) {
            val state = stateHolder.state().copy(searchResults = it)
            stateHolder.updateState(state)
            setState(BaseViewState.Data(IngredientSearchState(searchResults = it)))
        }
    }

    private fun onAddIngredient(event: IngredientSearchEvent.AddIngredient) = safeLaunch {
        val state = stateHolder.state()
        val ingredient = event.ingredient
        val ingredients = event.ingredients.toMutableList()
        ingredients.add(ingredient)
        setState(
            BaseViewState.Data(
                IngredientSearchState(
                    searchResults = state.searchResults,
                    ingredients = ingredients
                )
            )
        )
    }


    private fun onRemoveIngredient(event: IngredientSearchEvent.RemoveIngredient) = safeLaunch {
        val state = stateHolder.state()
        val ingredient = event.ingredient
        val ingredients = event.ingredients.toMutableList()
        ingredients.remove(ingredient)
        setState(
            BaseViewState.Data(
                IngredientSearchState(
                    searchResults = state.searchResults,
                    ingredients = ingredients
                )
            )
        )
    }

    private fun onCloseRecipeBuilder(event: IngredientSearchEvent.CloseIngredientBuilder) = safeLaunch {
        val state = stateHolder.state().copy(ingredients = event.ingredients)
        stateHolder.updateState(state)
        setState(BaseViewState.Data(IngredientSearchState(step = RecipeBuilderStep.COMPLETE)))
    }
}