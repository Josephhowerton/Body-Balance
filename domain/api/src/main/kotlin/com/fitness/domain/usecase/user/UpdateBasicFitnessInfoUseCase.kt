package com.fitness.domain.usecase.user

import enums.EFitnessHabits
import enums.EFitnessLevel
import usecase.DataStateUseCase

abstract class UpdateBasicFitnessInfoUseCase: DataStateUseCase<UpdateBasicFitnessInfoUseCase.Params, Unit>(){
    sealed class Params{
        data class UpdateRestrictions(val id: String, val level: EFitnessLevel): Params()
        data class UpdatePreferences(val id: String, val habits: List<EFitnessHabits>): Params()
    }

}