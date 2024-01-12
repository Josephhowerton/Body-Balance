package com.fitness.domain.usecase.user

import enums.EGender
import usecase.DataStateUseCase

abstract class UpdateBasicUserInfoUseCase: DataStateUseCase<UpdateBasicUserInfoUseCase.Params, Unit>(){
    sealed class Params{
        data class UpdateAge(val id: String, val age: Int): Params()
        data class UpdateGender(val id: String, val gender: EGender): Params()
        data class UpdateHeight(val id: String, val height: Double): Params()
        data class UpdateWeight(val id: String, val weight: Double): Params()
    }
}