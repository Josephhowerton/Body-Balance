package com.fitness.onboard.onboard.basic.viewmodel

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.model.user.UserPreferences
import com.fitness.domain.usecase.user.CreateBasicUserInfoUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.UpdateUserPreferencesUseCase
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
    private val updateUserPreferencesUseCase: UpdateUserPreferencesUseCase,
    private val createBasicUserInfoUseCase: CreateBasicUserInfoUseCase
) : IntentViewModel<BaseViewState<BasicInformationState>, BasicInformationEvent>() {

    init {
        setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.SYSTEM_OF_MEASUREMENTS)))
    }

    override fun onTriggerEvent(event: BasicInformationEvent) {
        when (event) {
            is BasicInformationEvent.SystemOfMeasurement -> getCurrentUserId(event)
            is BasicInformationEvent.GenderAge -> onGenderAge(event)
            is BasicInformationEvent.Weight -> onWeight(event)
            is BasicInformationEvent.Height -> onHeight(event)
            is BasicInformationEvent.Waist -> onWaist(event)
            is BasicInformationEvent.SaveBasicInformation -> getCurrentUserId(event)
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun getCurrentUserId(event: BasicInformationEvent) = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)) {
            when (event) {
                is BasicInformationEvent.SystemOfMeasurement -> onSystemOfMeasurement(it, event)
                else -> onVerify(it)
            }
        }
    }

    private fun onSystemOfMeasurement(
        id: String,
        event: BasicInformationEvent.SystemOfMeasurement
    ) = safeLaunch {

        stateHolder.updateState(stateHolder.getState().copy(preferredMeasurement = event.systemOfMeasurement))
        val params = UpdateUserPreferencesUseCase.Params(id = id, userPreferences = UserPreferences(systemOfMeasurement = event.systemOfMeasurement))

        execute(updateUserPreferencesUseCase(params = params)) {
            setState(
                BaseViewState.Data(
                    BasicInformationState(
                        units = stateHolder.getState().preferredMeasurement,
                        step = BasicInformationStep.GENDER_AGE
                    )
                )
            )
        }
    }

    private fun onGenderAge(event: BasicInformationEvent.GenderAge) {
        stateHolder.updateState(stateHolder.getState().copy(age = event.age, gender = event.gender))
        setState(
            BaseViewState.Data(
                BasicInformationState(
                    units = stateHolder.getState().preferredMeasurement,
                    step = BasicInformationStep.WEIGHT
                )
            )
        )
    }

    private fun onWeight(event: BasicInformationEvent.Weight) {
        stateHolder.updateState(stateHolder.getState().copy(weight = event.weight))
        setState(
            BaseViewState.Data(
                BasicInformationState(
                    units = stateHolder.getState().preferredMeasurement,
                    step = BasicInformationStep.HEIGHT
                )
            )
        )
    }

    private fun onHeight(event: BasicInformationEvent.Height) {
        stateHolder.updateState(stateHolder.getState().copy(height = event.height))
        setState(
            BaseViewState.Data(
                BasicInformationState(
                    units = stateHolder.getState().preferredMeasurement,
                    step = BasicInformationStep.WAIST
                )
            )
        )
    }

    private fun onWaist(event: BasicInformationEvent.Waist) {
        stateHolder.updateState(stateHolder.getState().copy(waist = event.waist))
        setState(
            BaseViewState.Data(
                BasicInformationState(
                    units = stateHolder.getState().preferredMeasurement,
                    step = BasicInformationStep.SAVE_BASIC_INFORMATION
                )
            )
        )
    }


    private fun onVerify(id: String) {
        val basicInformation = stateHolder.getState()
        val age = basicInformation.age
        val gender = basicInformation.gender
        val height = basicInformation.height
        val weight = basicInformation.weight
        val waist = basicInformation.waist
        val system = basicInformation.preferredMeasurement
        if (age != null &&
            gender != null &&
            height != null &&
            weight != null &&
            waist != null
        ) {
            val userBasicInfo = UserBasicInfo(
                userId = id,
                age = age,
                gender = gender,
                height = height,
                weight = weight,
                waist = waist,
            )
            onSaveBasicInformation(userBasicInfo)
        } else {
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveBasicInformation(info: UserBasicInfo) = safeLaunch {
        val param = CreateBasicUserInfoUseCase.Params(info)
        execute(createBasicUserInfoUseCase(param)) {
            setState(BaseViewState.Data(BasicInformationState(step = BasicInformationStep.COMPLETE)))
        }
    }
}