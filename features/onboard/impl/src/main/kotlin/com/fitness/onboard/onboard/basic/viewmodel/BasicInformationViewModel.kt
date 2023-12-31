package com.fitness.onboard.onboard.basic.viewmodel

import com.fitness.domain.usecase.cache.CreateBasicUserInfoUseCase
import com.fitness.onboard.onboard.basic.BasicInformationStateHolder
import com.fitness.onboard.util.OnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class BasicInformationViewModel @Inject constructor(
    private val stateHolder: BasicInformationStateHolder,
    private val createBasicUserInfoUseCase: CreateBasicUserInfoUseCase
): IntentViewModel<BaseViewState<BasicInformationState>, BasicInformationEvent>() {

    init {
        setState(
            BaseViewState.Data(
                BasicInformationState(BasicInformationStep.GENDER_AGE)
            )
        )
    }
    override fun onTriggerEvent(event: BasicInformationEvent) {
        when(event){
            is BasicInformationEvent.GenderAge -> onGenderAge(event)
            is BasicInformationEvent.Weight -> onWeight(event)
            is BasicInformationEvent.Height -> onHeight(event)
            is BasicInformationEvent.HealthConcerns-> onHealthConcerns(event)
            is BasicInformationEvent.DietaryPreferences -> onDietaryPreferences(event)
            is BasicInformationEvent.SaveBasicInformation -> onVerify()
        }
    }

    private fun onGenderAge(event: BasicInformationEvent.GenderAge) {
        stateHolder.updateState(stateHolder.getState().copy(age = event.age, gender = event.gender))
        setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.WEIGHT)))
    }

    private fun onWeight(event: BasicInformationEvent.Weight){
        stateHolder.updateState(stateHolder.getState().copy(weight = event.weight))
        setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.HEIGHT)))
    }

    private fun onHeight(event: BasicInformationEvent.Height){
        stateHolder.updateState(stateHolder.getState().copy(height = event.height))
        setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.HEALTH_CONCERNS)))
    }

    private fun onHealthConcerns(event: BasicInformationEvent.HealthConcerns){
        stateHolder.updateState(stateHolder.getState().copy(healthConcerns = event.healthConcerns))
        setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.DIETARY_PREFERENCES)))
    }

    private fun onDietaryPreferences(event: BasicInformationEvent.DietaryPreferences){
        stateHolder.updateState(stateHolder.getState().copy(dietaryPreferences = event.dietaryPreferences))
        setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.SAVE_BASIC_INFORMATION)))
    }

    private fun onVerify(){
        val basicInformation = stateHolder.getState()
        val age = basicInformation.age
        val gender = basicInformation.gender
        val height = basicInformation.height
        val weight = basicInformation.weight
        val dietaryPreferences = basicInformation.dietaryPreferences
        val healthConcerns = basicInformation.healthConcerns
        if(age != null &&
            gender != null &&
            height != null &&
            weight != null &&
            dietaryPreferences != null &&
            healthConcerns != null){
            onSaveBasicInformation(age, gender, height, weight, dietaryPreferences, healthConcerns)
        }else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveBasicInformation(
        age: Int,
        gender: String,
        height: Double,
        weight: Double,
        dietaryPreferences: List<String>,
        healthConcerns: List<String>
    ) = safeLaunch {
        val param = CreateBasicUserInfoUseCase.Params(age = age, gender = gender, height = height, weight = weight, dietaryPreferences = dietaryPreferences, healthConcerns = healthConcerns)
        execute(createBasicUserInfoUseCase(param)){
            setState(BaseViewState.Data(BasicInformationState(BasicInformationStep.COMPLETE)))
        }
    }
}