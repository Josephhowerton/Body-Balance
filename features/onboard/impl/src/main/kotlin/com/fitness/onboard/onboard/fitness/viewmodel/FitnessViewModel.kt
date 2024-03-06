package com.fitness.onboard.onboard.fitness.viewmodel

import com.fitness.domain.model.user.UserFitnessLevel
import com.fitness.domain.usecase.user.CreateUserBasicFitnessUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.fitness.FitnessStateHolder
import com.fitness.onboard.util.HABITS_MIN_SELECTION
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.isMinSelection
import com.fitness.onboard.util.toOnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.MinimumNumberOfSelectionFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class FitnessViewModel @Inject constructor(
    private val stateHolder: FitnessStateHolder,
    private val currentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createUserBasicFitnessUseCase: CreateUserBasicFitnessUseCase
): IntentViewModel<BaseViewState<FitnessState>, FitnessEvent>(){

    init { setState(BaseViewState.Data(FitnessState())) }
    override fun onTriggerEvent(event: FitnessEvent) {
        when(event){
            is FitnessEvent.FitnessLevel -> onFitnessLevels(event)
            is FitnessEvent.Habits -> onHabits(event)
            is FitnessEvent.SaveFitnessInfo -> getCurrentUserId()
            is FitnessEvent.DismissDialog -> onDismissDialog()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onDismissDialog(){
        val state = stateHolder.getState()
        setState(BaseViewState.Data(FitnessState(step = state.currentStep, habits = state.habits)))
    }

    private fun onFitnessLevels(event: FitnessEvent.FitnessLevel){
        stateHolder.updateState(stateHolder.getState().copy(currentStep = FitnessStep.HABITS, level = event.level))
        setState(BaseViewState.Data(FitnessState(FitnessStep.HABITS)))
    }

    private fun onHabits(event: FitnessEvent.Habits){
        stateHolder.updateState(stateHolder.getState().copy(habits = event.habits))
        if(isMinSelection(event.habits.size, HABITS_MIN_SELECTION)){
            setState(BaseViewState.Data(FitnessState(FitnessStep.SAVE_FITNESS_INFO)))
        }
        else{
            setState(BaseViewState.Error(MinimumNumberOfSelectionFailure(minSelection = HABITS_MIN_SELECTION)))
        }
    }

    private fun getCurrentUserId() = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)){
            onVerify(it)
        }
    }

    private fun onVerify(id: String){
        val fitness = stateHolder.getState()
        val level = fitness.level
        val habits = fitness.habits
        if(level != null && habits.isNotEmpty()){
            val userBasicInfo = UserFitnessLevel(id, level, habits)
            onSaveFitnessLevelsInfo(userBasicInfo)
        }else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveFitnessLevelsInfo(userBasicInfoDomain: UserFitnessLevel) = safeLaunch {
        val param = CreateUserBasicFitnessUseCase.Params(userBasicInfoDomain)
        execute(createUserBasicFitnessUseCase(param)){
            setState(BaseViewState.Data(FitnessState(FitnessStep.COMPLETE)))
        }
    }
}