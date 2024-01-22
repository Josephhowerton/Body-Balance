package com.fitness.recipebuilder.screens.recipes

import com.fitness.domain.model.nutrition.getDateAsLong
import com.fitness.domain.model.nutrition.getTimeFromDate
import com.fitness.domain.usecase.nutrition.GetEditableNutritionRecordsUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import com.fitness.recipebuilder.util.RecipeSelectionStep
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeSelectionViewModel @Inject constructor(
    private val stateHolder: RecipeBuilderStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getEditableNutritionRecordsUseCase: GetEditableNutritionRecordsUseCase
) : IntentViewModel<BaseViewState<RecipeSelectionState>, RecipeSelectionEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: RecipeSelectionEvent) {
        when (event) {
            is RecipeSelectionEvent.RecipeSelected -> onNutritionRecordSelected(event)
            is RecipeSelectionEvent.CreateNewRecipe -> onCreateNewRecord()
            is RecipeSelectionEvent.CancelCreate -> onCancelCreateRecord()
            is RecipeSelectionEvent.GiveName -> onGiveName(event)
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() = onGetCurrentUserId()

    private fun onGetCurrentUserId() = safeLaunch {
        execute(getCurrentUserIdUseCase(GetCurrentUserIdUseCase.Params)){
            onGetEditableRecipes(it)
        }
    }

    private fun onGetEditableRecipes(userId: String) = safeLaunch {
        execute(getEditableNutritionRecordsUseCase(GetEditableNutritionRecordsUseCase.Params(userId))){
            stateHolder.updateState(stateHolder.state().copy(myRecipes = it))
            setState(BaseViewState.Data(RecipeSelectionState(myRecipes = it, step = RecipeSelectionStep.PENDING)))
        }
    }
    private fun onNutritionRecordSelected(event: RecipeSelectionEvent.RecipeSelected) {
        val nutrition = event.nutrition
        val (hour, minute) = nutrition.getTimeFromDate()
        val state = stateHolder.state().copy(
            name = nutrition.recipe.name,
            date = nutrition.getDateAsLong(),
            hour = hour,
            minute = minute,
            type = nutrition.mealType,
            recordId = nutrition.recordId,
            recipe = nutrition.recipe,
            ingredients = nutrition.recipe.ingredients?.toMutableList() ?: mutableListOf(),
            instructions = nutrition.recipe.instructions?.toMutableList() ?: mutableListOf()
        )
        stateHolder.updateState(state)
        setState(BaseViewState.Data(RecipeSelectionState(step = RecipeSelectionStep.MODIFY)))
    }

    private fun onCreateNewRecord() {
        setState(BaseViewState.Data(RecipeSelectionState(step = RecipeSelectionStep.NAME)))
    }

    private fun onGiveName(event: RecipeSelectionEvent.GiveName) {
        stateHolder.clearState()
        stateHolder.updateState(stateHolder.state().copy(name = event.name))
        setState(BaseViewState.Data(RecipeSelectionState(step = RecipeSelectionStep.CREATE)))
    }

    private fun onCancelCreateRecord() {
        setState(BaseViewState.Data(RecipeSelectionState(myRecipes = stateHolder.state().myRecipes, step = RecipeSelectionStep.PENDING)))
    }
}