package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicInfoCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateBasicUserInfoUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : CreateBasicUserInfoUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createUserBasicInfo(info = params.userBasicInfoDomain.toUserBasicInfoCache())
        emit(result)
    }
}