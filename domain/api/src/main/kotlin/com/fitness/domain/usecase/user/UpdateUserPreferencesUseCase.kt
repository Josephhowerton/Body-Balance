package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserPreferences
import usecase.DataStateUseCase

abstract class UpdateUserPreferencesUseCase: DataStateUseCase<UpdateUserPreferencesUseCase.Params, Unit>() {
    data class Params(
        val id: String,
        val userPreferences: UserPreferences
    )
}

