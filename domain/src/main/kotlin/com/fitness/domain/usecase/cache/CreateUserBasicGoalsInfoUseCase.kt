package com.fitness.domain.usecase.cache

import com.fitness.data.extensions.toUserBasicGoalsInfoCache
import com.fitness.data.model.domain.user.UserBasicGoalsInfoDomain
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserBasicGoalsInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<CreateUserBasicGoalsInfoUseCase.Params, Unit>() {
    data class Params(val userBasicGoalsInfo: UserBasicGoalsInfoDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createUserBasicGoalsInfo(info = params.userBasicGoalsInfo.toUserBasicGoalsInfoCache())
        emit(result)
    }
}