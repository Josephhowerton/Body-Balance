package com.fitness.domain.usecase.cache

import com.fitness.data.extensions.toUserFitnessLevelCache
import com.fitness.data.model.domain.user.UserFitnessLevelDomain
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserBasicFitnessUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<CreateUserBasicFitnessUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserFitnessLevelDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createBasicFitnessInfo(info = params.userBasicInfoDomain.toUserFitnessLevelCache())
        emit(result)
    }
}

