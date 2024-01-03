package com.fitness.onboard.onboard.nutrition.viewmodel

import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.domain.usecase.cache.CreateUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.nutrition.NutritionStateHolder
import com.fitness.onboard.util.OnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val stateHolder: NutritionStateHolder,
    private val currentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createUserBasicNutritionInfoUseCase: CreateUserBasicNutritionInfoUseCase
): IntentViewModel<BaseViewState<NutritionState>, NutritionEvent>(){

    init { setState(BaseViewState.Data(NutritionState())) }
    override fun onTriggerEvent(event: NutritionEvent) {
        when(event){
            is NutritionEvent.NutritionPreferences -> onNutritionPreferences (event)
            is NutritionEvent.DietaryRestrictions -> onDietaryRestrictions(event)
            is NutritionEvent.SaveFitnessInfo -> getCurrentUserId()
        }
    }

    private fun onNutritionPreferences (event: NutritionEvent.NutritionPreferences){
        stateHolder.updateState(stateHolder.getState().copy(preferences = event.preferences))
        setState(BaseViewState.Data(NutritionState(NutritionStep.DIETARY_RESTRICTIONS)))
    }

    private fun onDietaryRestrictions(event: NutritionEvent.DietaryRestrictions){
        stateHolder.updateState(stateHolder.getState().copy(restrictions = event.restrictions))
        setState(BaseViewState.Data(NutritionState(NutritionStep.SAVE_INFO)))
    }

    private fun getCurrentUserId() = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)){
            onVerify(it)
        }
    }

    private fun onVerify(id: String){
        val fitness = stateHolder.getState()
        val restrictions = fitness.restrictions
        val preferences = fitness.preferences
        if(restrictions != null && preferences != null){
            val userBasicInfo = UserBasicNutritionInfoDomain(id, restrictions=restrictions, preferences=preferences)
            onSaveFitnessLevelsInfo(userBasicInfo)
        }else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveFitnessLevelsInfo(userBasicInfoDomain: UserBasicNutritionInfoDomain) = safeLaunch {
        val param = CreateUserBasicNutritionInfoUseCase.Params(userBasicInfoDomain)
        execute(createUserBasicNutritionInfoUseCase(param)){
            setState(BaseViewState.Data(NutritionState(NutritionStep.COMPLETE)))
        }
    }
}