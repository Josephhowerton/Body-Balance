package com.fitness.search.screens.confirmation

import com.fitness.domain.usecase.nutrition.CreateNutritionRecordUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.search.SearchRecipeStep
import com.fitness.search.RecipeSearchStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.GenericFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SaveConfirmViewModel @Inject constructor(
    private val searchStateHolder: RecipeSearchStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createNutritionRecordUseCase: CreateNutritionRecordUseCase,
) : IntentViewModel<BaseViewState<SaveAndConfirmState>, SaveAndConfirmEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: SaveAndConfirmEvent) {
        if(event is SaveAndConfirmEvent.Save){
            onSaveMeal()
        }else {
            handleError(GenericFailure())
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() = setState(BaseViewState.Data(SaveAndConfirmState(step = SearchRecipeStep.PENDING)))

    private fun onSaveMeal() = safeLaunch {
        execute(getCurrentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            saveMealRecord(it)
        }
    }

    private fun saveMealRecord(userId: String) = safeLaunch {
        val state = searchStateHolder.state()
        val params = CreateNutritionRecordUseCase.Params(
            userId = userId,
            recipe = state.recipe,
            date = state.date,
            hour = state.hour,
            minute = state.minute,
            mealType = state.type
        )

        execute(createNutritionRecordUseCase(params)) {
            setState(
                BaseViewState.Data(
                    SaveAndConfirmState(
                        step = SearchRecipeStep.COMPLETE,
                        date = state.date,
                        hour = state.hour,
                        minute = state.minute
                    )
                )
            )
        }
    }

}