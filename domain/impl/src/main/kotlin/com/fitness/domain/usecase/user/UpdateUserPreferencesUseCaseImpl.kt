package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserPreferencesCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateUserPreferencesUseCaseImpl@Inject constructor(private val userRepository: UserRepository): UpdateUserPreferencesUseCase() {
    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        emit(userRepository.updateUserPreferences(params.id, params.userPreferences.toUserPreferencesCache()))
    }

}