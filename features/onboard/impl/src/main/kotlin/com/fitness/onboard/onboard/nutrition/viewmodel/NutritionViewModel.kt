package com.fitness.onboard.onboard.nutrition.viewmodel

import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.domain.usecase.cache.CreateUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.nutrition.NutritionStateHolder
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.toOnboardFailure
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
            is NutritionEvent.HealthLabels -> onNutritionPreferences (event)
            is NutritionEvent.DietaryRestrictions -> onDietaryRestrictions(event)
            is NutritionEvent.CuisineType -> onCuisineType(event)
            is NutritionEvent.SaveFitnessInfo -> getCurrentUserId()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onNutritionPreferences (event: NutritionEvent.HealthLabels){
        stateHolder.updateState(stateHolder.getState().copy(labels = event.labels))
        setState(BaseViewState.Data(NutritionState(NutritionStep.DIETARY_RESTRICTIONS)))
    }

    private fun onDietaryRestrictions(event: NutritionEvent.DietaryRestrictions){
        stateHolder.updateState(stateHolder.getState().copy(restrictions = event.restrictions))
        setState(BaseViewState.Data(NutritionState(NutritionStep.CUISINE_TYPE)))
    }

    private fun onCuisineType(event: NutritionEvent.CuisineType){
        stateHolder.updateState(stateHolder.getState().copy(cuisineType = event.cuisineType))
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
        val labels = fitness.labels
        val cuisineType = fitness.cuisineType
        if(restrictions != null && labels != null && cuisineType != null){
            val userBasicInfo = UserBasicNutritionInfoDomain(id, restrictions=restrictions, labels=labels, cuisineType = cuisineType)
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