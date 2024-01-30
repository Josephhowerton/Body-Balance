package com.fitness.domain.usecase.user

import enums.EFitnessInterest
import enums.EPhysicalActivityLevel
import usecase.DataStateUseCase

abstract class UpdateBasicFitnessInfoUseCase: DataStateUseCase<UpdateBasicFitnessInfoUseCase.Params, Unit>(){
    sealed class Params{
        data class UpdateRestrictions(val id: String, val level: EPhysicalActivityLevel): Params()
        data class UpdatePreferences(val id: String, val habits: List<EFitnessInterest>): Params()
    }

}