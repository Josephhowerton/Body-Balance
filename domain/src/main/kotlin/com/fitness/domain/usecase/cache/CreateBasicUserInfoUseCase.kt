package com.fitness.domain.usecase.cache

import com.fitness.data.extensions.toUserBasicInfoCache
import com.fitness.data.model.domain.user.UserBasicInfoDomain
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateBasicUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<CreateBasicUserInfoUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserBasicInfoDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createUserBasicInfo(info = params.userBasicInfoDomain.toUserBasicInfoCache())
        emit(result)
    }
}