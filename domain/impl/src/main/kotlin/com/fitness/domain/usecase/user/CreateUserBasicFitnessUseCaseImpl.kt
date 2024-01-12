package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserFitnessLevelCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserBasicFitnessUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    CreateUserBasicFitnessUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createBasicFitnessInfo(info = params.userBasicInfoDomain.toUserFitnessLevelCache())
        emit(result)
    }
}

