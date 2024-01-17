package com.fitness.search.screens.recipe

import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.search.SearchRecipeStep
import com.fitness.search.RecipeSearchStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import util.RecipeFetchUseCase
import util.RecipeSearchUseCase
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val searchStateHolder: RecipeSearchStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getBasicNutritionInfoCacheInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    @RecipeSearchUseCase private val edamamRecipeSearchUseCase: EdamamRecipeSearchUseCase,
    @RecipeFetchUseCase private val edamamFetchRecipesUseCase: EdamamRecipeSearchUseCase,
) : IntentViewModel<BaseViewState<RecipeSearchState>, RecipeSearchEvent>() {

    init { initialize() }

    override fun onTriggerEvent(event: RecipeSearchEvent) {
        when (event) {
            is RecipeSearchEvent.AutoComplete -> onAutoComplete(event)

            is RecipeSearchEvent.Search -> onRecipeSearch(event)

            is RecipeSearchEvent.RecipeSelected -> onRecipeSelected(event)
        }
    }

    private fun initialize() = safeLaunch {
        execute(getCurrentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            currentUserNutritionalInfo(it)
        }
    }

    private fun currentUserNutritionalInfo(id: String) = safeLaunch {
        val params = GetUserBasicNutritionInfoUseCase.Params(id = id)
        execute(getBasicNutritionInfoCacheInfoUseCase(params)) {
            onFetchContentFromCache(it)
        }
    }

    private fun onFetchContentFromCache(nutrition: UserBasicNutritionInfo) = safeLaunch {
        val param = EdamamRecipeSearchUseCase.Params(search = "", cuisineType = nutrition.cuisineTypeApi)
        execute(edamamFetchRecipesUseCase(param)) {
            val state = searchStateHolder.state().copy(recipesSearchResults = it)
            searchStateHolder.updateState(state)
            setState(BaseViewState.Data(RecipeSearchState(searchResults = state.recipesSearchResults)))
        }
    }

    private fun onAutoComplete(event: RecipeSearchEvent.AutoComplete) = safeLaunch {
        val param = EdamamAutoCompleteUseCase.Params(event.search)
        call(edamamAutoCompleteUseCase(param)) {
            val state = searchStateHolder.state()
            setState(
                BaseViewState.Data(
                    RecipeSearchState(
                        autoComplete = it,
                        searchResults = state.recipesSearchResults
                    )
                )
            )
        }
    }

    private fun onRecipeSearch(event: RecipeSearchEvent.Search) = safeLaunch {
        searchStateHolder.updateState(
            searchStateHolder.state().copy(recipesSearchResults = emptyList())
        )
        val param = EdamamRecipeSearchUseCase.Params(event.search)
        execute(edamamRecipeSearchUseCase(param)) {
            val state = searchStateHolder.state().copy(recipesSearchResults = it)
            searchStateHolder.updateState(state)
            setState(BaseViewState.Data(RecipeSearchState(searchResults = it)))
        }
    }

    private fun onRecipeSelected(event: RecipeSearchEvent.RecipeSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(recipe = event.recipe)
        searchStateHolder.updateState(state)
        setState(
            BaseViewState.Data(
                RecipeSearchState(
                    autoComplete = emptyList(),
                    searchResults = state.recipesSearchResults,
                    step = SearchRecipeStep.COMPLETE,
                )
            )
        )
    }
}