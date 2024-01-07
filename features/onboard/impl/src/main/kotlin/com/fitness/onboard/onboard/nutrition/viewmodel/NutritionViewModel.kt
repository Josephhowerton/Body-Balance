package com.fitness.onboard.onboard.nutrition.viewmodel

import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.domain.usecase.cache.CreateUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessState
import com.fitness.onboard.onboard.nutrition.NutritionStateHolder
import com.fitness.onboard.util.CUISINE_TYPE_MIN_SELECTION
import com.fitness.onboard.util.DIETARY_RESTRICTIONS_MIN_SELECTION
import com.fitness.onboard.util.GOALS_MIN_SELECTION
import com.fitness.onboard.util.HEALTH_LABELS_MIN_SELECTION
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.isMinSelection
import com.fitness.onboard.util.toOnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.MinimumNumberOfSelectionFailure
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
            is NutritionEvent.HealthLabels -> onHealthLabels (event)
            is NutritionEvent.DietaryRestrictions -> onDietaryRestrictions(event)
            is NutritionEvent.CuisineType -> onCuisineType(event)
            is NutritionEvent.SaveFitnessInfo -> getCurrentUserId()
            is NutritionEvent.DismissDialog -> onDismissDialog()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onDismissDialog(){
        val state = stateHolder.getState()
        setState(BaseViewState.Data(NutritionState(step =  state.currentStep, labels = state.labels, restrictions = state.restrictions, cuisineType = state.cuisineType)))
    }

    private fun onHealthLabels (event: NutritionEvent.HealthLabels){
        stateHolder.updateState(stateHolder.getState().copy(labels = event.labels))
        if (isMinSelection(event.labels.size, HEALTH_LABELS_MIN_SELECTION)) {
            setState(BaseViewState.Data(NutritionState(NutritionStep.DIETARY_RESTRICTIONS)))
            stateHolder.updateState(stateHolder.getState().copy(currentStep = NutritionStep.DIETARY_RESTRICTIONS))
        }
        else{
            setState(BaseViewState.Error(MinimumNumberOfSelectionFailure(minSelection = HEALTH_LABELS_MIN_SELECTION)))
        }
    }

    private fun onDietaryRestrictions(event: NutritionEvent.DietaryRestrictions){
        stateHolder.updateState(stateHolder.getState().copy(restrictions = event.restrictions))
        if (isMinSelection(event.restrictions.size, DIETARY_RESTRICTIONS_MIN_SELECTION)) {
            setState(BaseViewState.Data(NutritionState(NutritionStep.CUISINE_TYPE)))
            stateHolder.updateState(stateHolder.getState().copy(currentStep = NutritionStep.CUISINE_TYPE))
        }
        else{
            setState(BaseViewState.Error(MinimumNumberOfSelectionFailure(minSelection = DIETARY_RESTRICTIONS_MIN_SELECTION)))
        }
    }

    private fun onCuisineType(event: NutritionEvent.CuisineType){
        stateHolder.updateState(stateHolder.getState().copy(cuisineType = event.cuisineType))
        if (isMinSelection(event.cuisineType.size, CUISINE_TYPE_MIN_SELECTION)) {
            setState(BaseViewState.Data(NutritionState(NutritionStep.SAVE_INFO)))
        }
        else{
            setState(BaseViewState.Error(MinimumNumberOfSelectionFailure(minSelection = CUISINE_TYPE_MIN_SELECTION)))
        }
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
        if(restrictions.isNotEmpty() && labels.isNotEmpty() && cuisineType.isNotEmpty()){
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