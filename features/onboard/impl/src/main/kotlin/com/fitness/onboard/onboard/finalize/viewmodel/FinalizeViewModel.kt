package com.fitness.onboard.onboard.finalize.viewmodel

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.usecase.metrics.CreateUserBodyMetricsFromUserInfoUseCase
import com.fitness.domain.usecase.user.GetBasicUserInfoUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetCurrentUserUseCase
import com.fitness.onboard.onboard.finalize.FinalizeStateHolder
import com.fitness.onboard.util.OnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import enums.SystemOfMeasurement
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class FinalizeViewModel @Inject constructor(
    private val currentUserIdUseCase: GetCurrentUserIdUseCase,
    private val currentUserUseCase: GetCurrentUserUseCase,
    private val basicUserInfoUseCase: GetBasicUserInfoUseCase,
    private val createRecommendedMetricsUseCase: CreateUserBodyMetricsFromUserInfoUseCase,
    private val stateHolder: FinalizeStateHolder
): IntentViewModel<BaseViewState<FinalizeState>, FinalizeEvent>() {

    init {
        setState(BaseViewState.Data(FinalizeState()))
    }
    override fun onTriggerEvent(event: FinalizeEvent) {
        when(event){
            FinalizeEvent.Initialized -> onGetCurrentUserId()
            FinalizeEvent.SaveRecommendedMetrics -> onSaveRecommended()
        }
    }


    private fun onGetCurrentUserId() = safeLaunch {
        execute(currentUserIdUseCase(params = GetCurrentUserIdUseCase.Params)){
            onGetCurrentUser(id = it)
        }
    }

    private fun onGetCurrentUser(id: String) = safeLaunch {
        execute(currentUserUseCase(params = GetCurrentUserUseCase.Params(id))){
            val state = stateHolder.getState().copy(unitSystem = it.userPreferences.systemOfMeasurement)
            stateHolder.updateState(newState = state)
            onGetCurrentUserBasicInfo(id = id)
        }
    }

    private fun onGetCurrentUserBasicInfo(id: String) = safeLaunch {
        execute(basicUserInfoUseCase(params = GetBasicUserInfoUseCase.Params(id))){
            val state = stateHolder.getState().copy(userBasicInfo = it, currentStep = FinalizeStep.SAVE_RECOMMENDED)
            stateHolder.updateState(newState = state)
            setState(BaseViewState.Data(FinalizeState(finalizeStep = FinalizeStep.SAVE_RECOMMENDED)))
        }
    }

    private fun onSaveRecommended(){
        val state = stateHolder.getState()
        val basicInfo = state.userBasicInfo
        val unitSystem = state.unitSystem
        if(basicInfo != null && unitSystem != null){
            saveRecommended(basicInfo = basicInfo, unitSystem = unitSystem)
        }else{
            setState(state = BaseViewState.Error(failure = OnboardFailure.UnknownFailure()))
        }
    }

    private fun saveRecommended(basicInfo: UserBasicInfo, unitSystem: SystemOfMeasurement) = safeLaunch {
        execute(createRecommendedMetricsUseCase(params = CreateUserBodyMetricsFromUserInfoUseCase.Params(info = basicInfo, unitSystem = unitSystem))){
            val state = stateHolder.getState().copy(currentStep = FinalizeStep.COMPLETE)
            stateHolder.updateState(newState = state)
            setState(BaseViewState.Data(FinalizeState(finalizeStep = FinalizeStep.COMPLETE)))
        }
    }
}