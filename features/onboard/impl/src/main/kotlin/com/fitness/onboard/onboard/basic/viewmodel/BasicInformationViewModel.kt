package com.fitness.onboard.onboard.basic.viewmodel

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.usecase.user.CreateBasicUserInfoUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.basic.BasicInformationStateHolder
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.toOnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class BasicInformationViewModel @Inject constructor(
    private val stateHolder: BasicInformationStateHolder,
    private val currentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createBasicUserInfoUseCase: CreateBasicUserInfoUseCase
) : IntentViewModel<BaseViewState<BasicInformationState>, BasicInformationEvent>() {

    init {
        setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.GENDER_AGE)))
    }

    override fun onTriggerEvent(event: BasicInformationEvent) {
        when (event) {
            is BasicInformationEvent.GenderAge -> onGenderAge(event)
            is BasicInformationEvent.Weight -> onWeight(event)
            is BasicInformationEvent.Height -> onHeight(event)
            is BasicInformationEvent.SaveBasicInformation -> getCurrentUserId()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onGenderAge(event: BasicInformationEvent.GenderAge) {
        stateHolder.updateState(stateHolder.getState().copy(age = event.age, gender = event.gender))
        setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.WEIGHT)))
    }

    private fun onWeight(event: BasicInformationEvent.Weight) {
        stateHolder.updateState(stateHolder.getState().copy(weight = event.weight))
        setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.HEIGHT)))
    }

    private fun onHeight(event: BasicInformationEvent.Height) {
        stateHolder.updateState(stateHolder.getState().copy(height = event.height))
        setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.SAVE_BASIC_INFORMATION)))
    }

    private fun getCurrentUserId() = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            onVerify(it)
        }
    }

    private fun onVerify(id: String) {
        val basicInformation = stateHolder.getState()
        val age = basicInformation.age
        val gender = basicInformation.gender
        val height = basicInformation.height
        val weight = basicInformation.weight
        if (age != null && gender != null && height != null && weight != null) {
            val userBasicInfo = UserBasicInfo(id, age, gender, height, weight)
            onSaveBasicInformation(userBasicInfo)
        } else {
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveBasicInformation(userBasicInfoDomain: UserBasicInfo) = safeLaunch {
        val param = CreateBasicUserInfoUseCase.Params(userBasicInfoDomain)
        execute(createBasicUserInfoUseCase(param)) {
            setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.COMPLETE)))
        }
    }
}