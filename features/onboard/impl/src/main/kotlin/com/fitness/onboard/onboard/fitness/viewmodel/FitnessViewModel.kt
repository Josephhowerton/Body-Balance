package com.fitness.onboard.onboard.fitness.viewmodel

import com.fitness.data.model.domain.user.UserFitnessLevelDomain
import com.fitness.domain.usecase.cache.CreateUserBasicFitnessUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.fitness.FitnessStateHolder
import com.fitness.onboard.util.OnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
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
        }
    }

    private fun onFitnessLevels(event: FitnessEvent.FitnessLevel){
        stateHolder.updateState(stateHolder.getState().copy(level = event.level))
        setState(BaseViewState.Data(FitnessState(FitnessStep.HABITS)))
    }

    private fun onHabits(event: FitnessEvent.Habits){
        stateHolder.updateState(stateHolder.getState().copy(habits = event.habits))
        setState(BaseViewState.Data(FitnessState(FitnessStep.SAVE_FITNESS_INFO)))
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
        if(level != null && habits != null){
            val userBasicInfo = UserFitnessLevelDomain(id, level, habits)
            onSaveFitnessLevelsInfo(userBasicInfo)
        }else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveFitnessLevelsInfo(userBasicInfoDomain: UserFitnessLevelDomain) = safeLaunch {
        val param = CreateUserBasicFitnessUseCase.Params(userBasicInfoDomain)
        execute(createUserBasicFitnessUseCase(param)){
            setState(BaseViewState.Data(FitnessState(FitnessStep.COMPLETE)))
        }
    }
}