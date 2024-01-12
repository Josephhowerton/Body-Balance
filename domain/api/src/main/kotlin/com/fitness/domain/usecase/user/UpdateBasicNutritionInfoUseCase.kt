package com.fitness.domain.usecase.user

import enums.EDietaryRestrictions
import enums.ENutritionPreferences
import usecase.DataStateUseCase

abstract class UpdateBasicNutritionInfoUseCase: DataStateUseCase<UpdateBasicNutritionInfoUseCase.Params, Unit>(){
    sealed class Params{
        data class UpdateRestrictions(val id: String, val restrictions: List<EDietaryRestrictions>): Params()
        data class UpdatePreferences(val id: String, val preferences: List<ENutritionPreferences>): Params()
    }

}

