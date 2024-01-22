package com.fitness.recipebuilder.screens.builder

import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeBuilderViewModel @Inject constructor(
    private val stateHolder: RecipeBuilderStateHolder
) :
    IntentViewModel<BaseViewState<RecipeBuilderState>, RecipeBuilderEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: RecipeBuilderEvent) {
        when (event) {
            is RecipeBuilderEvent.EditName -> onEditName(event)
            is RecipeBuilderEvent.GenerateInstructions -> onGenerateInstructions()
            is RecipeBuilderEvent.EditDate -> {}
            is RecipeBuilderEvent.EditType -> {}
            is RecipeBuilderEvent.EditInstructions -> {}
            is RecipeBuilderEvent.RemoveIngredient -> {}
            is RecipeBuilderEvent.SaveNutritionRecord -> {}
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() {
        val state = stateHolder.state()
        setState(
            BaseViewState.Data(
                RecipeBuilderState(
                    name = state.name ?: "Create a name",
                    date = state.date,
                    hour = state.hour,
                    minute = state.minute,
                    type = state.type,
                    ingredients = state.ingredients,
                    instructions = state.instructions
                )
            )
        )
    }

    private fun verified() {

    }

    private fun onEditName(event: RecipeBuilderEvent.EditName) = safeLaunch {

    }

    private fun onGenerateInstructions() = safeLaunch {

    }
}