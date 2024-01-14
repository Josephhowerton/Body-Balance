package com.fitness.search.nutrition.viewmodel

import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.search.nutrition.RecipeStep
import com.fitness.search.nutrition.SearchStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import util.RecipeFetchUseCase
import util.RecipeSearchUseCase
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionSearchViewModel @Inject constructor(
    private val searchStateHolder: SearchStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getBasicNutritionInfoCacheInfoUseCase: GetUserBasicNutritionInfoUseCase,
    private val edamamAutoCompleteUseCase: EdamamAutoCompleteUseCase,
    @RecipeSearchUseCase private val edamamRecipeSearchUseCase: EdamamRecipeSearchUseCase,
    @RecipeFetchUseCase private val edamamFetchRecipesUseCase: EdamamRecipeSearchUseCase
) : IntentViewModel<BaseViewState<NutritionSearchState>, NutritionSearchEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: NutritionSearchEvent) {
        when (event) {
            is NutritionSearchEvent.AutoComplete -> onAutoComplete(event)

            is NutritionSearchEvent.Search -> {
                val state = searchStateHolder.state().copy(recipes = emptyList(), food = emptyList())
                searchStateHolder.updateState(state)
                onRecipeSearch(event)
            }

            is NutritionSearchEvent.RecipeSelected -> onRecipeSelected(event)

            is NutritionSearchEvent.DateSelected -> onDateSelected(event)

            is NutritionSearchEvent.MealTypeSelected -> onMealTypeSelected(event)

            is NutritionSearchEvent.SaveRecipe -> onSaveRecipe(event)
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
        execute(getBasicNutritionInfoCacheInfoUseCase(params)) {
            onFetchContentFromCache(it)
        }
    }

    private fun onFetchContentFromCache(nutrition: UserBasicNutritionInfo) = safeLaunch {
        val param =
            EdamamRecipeSearchUseCase.Params(search = "", cuisineType = nutrition.cuisineTypeApi)
        execute(edamamFetchRecipesUseCase(param)) {
            val state = searchStateHolder.state().copy(recipes = it)
            searchStateHolder.updateState(state)
            setState(BaseViewState.Data(NutritionSearchState(searchResults = state.recipes)))
        }
    }

    private fun onAutoComplete(event: NutritionSearchEvent.AutoComplete) = safeLaunch {
        val param = EdamamAutoCompleteUseCase.Params(event.search)
        call(edamamAutoCompleteUseCase(param)) {
            val state = searchStateHolder.state()
            setState(BaseViewState.Data(NutritionSearchState(autoComplete = it, searchResults = state.recipes)))
        }
    }


    private fun onRecipeSearch(event: NutritionSearchEvent.Search) = safeLaunch {
        val param = EdamamRecipeSearchUseCase.Params(event.search)
        execute(edamamRecipeSearchUseCase(param)) {
            val state = searchStateHolder.state().copy(recipes = it)
            searchStateHolder.updateState(state)
            setState(BaseViewState.Data(NutritionSearchState(searchResults = it)))
        }
    }

    private fun onRecipeSelected(event: NutritionSearchEvent.RecipeSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(recipe = event.recipe, step = RecipeStep.SELECT_DATE)
        searchStateHolder.updateState(state)
        setState(
            BaseViewState.Data(
                NutritionSearchState(
                    recipeToSave = state.recipe,
                    autoComplete = emptyList(),
                    searchResults = state.recipes,
                    step = RecipeStep.SELECT_DATE,
                )
            )
        )
    }

    private fun onDateSelected(event: NutritionSearchEvent.DateSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(date = event.date, step = RecipeStep.SELECT_MEAL_TYPE)
        searchStateHolder.updateState(state)
        setState(
            BaseViewState.Data(
                NutritionSearchState(
                    recipeToSave = state.recipe,
                    autoComplete = emptyList(),
                    searchResults = state.recipes,
                    step = RecipeStep.SELECT_MEAL_TYPE,
                )
            )
        )
    }

    private fun onMealTypeSelected(event: NutritionSearchEvent.MealTypeSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(type = event.type, step = RecipeStep.SAVE)
        searchStateHolder.updateState(state)
        setState(
            BaseViewState.Data(
                NutritionSearchState(
                    recipeToSave = state.recipe,
                    autoComplete = emptyList(),
                    searchResults = state.recipes,
                    step = RecipeStep.SAVE,
                )
            )
        )
    }

    private fun onSaveRecipe(event: NutritionSearchEvent.SaveRecipe) = safeLaunch {

    }


}