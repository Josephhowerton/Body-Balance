package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicGoalsInfoCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserBasicGoalsInfoUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    CreateUserBasicGoalsInfoUseCase() {


    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createUserBasicGoalsInfo(info = params.userBasicGoalsInfo.toUserBasicGoalsInfoCache())
        emit(result)
    }
}