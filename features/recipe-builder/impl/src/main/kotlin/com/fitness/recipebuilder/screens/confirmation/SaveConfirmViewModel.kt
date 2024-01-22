package com.fitness.recipebuilder.screens.confirmation

import com.fitness.domain.usecase.nutrition.CreateNutritionRecordUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import com.fitness.recipebuilder.util.RecipeBuilderStep
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.GenericFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SaveConfirmViewModel @Inject constructor(
    private val stateHolder: RecipeBuilderStateHolder,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createNutritionRecordUseCase: CreateNutritionRecordUseCase,
) : IntentViewModel<BaseViewState<SaveAndConfirmState>, SaveAndConfirmEvent>() {

    init {
        initialize()
    }

    override fun onTriggerEvent(event: SaveAndConfirmEvent) {
        if(event is SaveAndConfirmEvent.Save){
            onSaveNutritionRecord()
        }else {
            handleError(GenericFailure())
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        exception.printStackTrace()
    }

    private fun initialize() = setState(BaseViewState.Data(SaveAndConfirmState(step = RecipeBuilderStep.PENDING)))

    private fun onSaveNutritionRecord() = safeLaunch {
        execute(getCurrentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            onVerify(it)
        }
    }

    private fun onVerify(id: String){
        val state = stateHolder.state()
        val date = state.date
        val hour = state.hour
        val minute = state.minute
        val type = state.type
        if(date != null && hour != null && minute != null && type != null){
//            val params = CreateNutritionRecordUseCase.Params(
//                userId = id,
//                date = state.date,
//                hour = state.hour,
//                minute = state.minute,
//                mealType = state.type
//            )
//            saveNutritionRecord(params)
        }else{
            setState(BaseViewState.Error(GenericFailure()))
        }
    }

    private fun saveNutritionRecord(params: CreateNutritionRecordUseCase.Params) = safeLaunch {
        val state = stateHolder.state()
        execute(createNutritionRecordUseCase(params)) {
            setState(
                BaseViewState.Data(
                    SaveAndConfirmState(
                        step = RecipeBuilderStep.COMPLETE,
                        date = state.date,
                        hour = state.hour,
                        minute = state.minute
                    )
                )
            )
        }
    }

}