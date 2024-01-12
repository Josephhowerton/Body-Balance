package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject
class DeleteBasicUserInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : DeleteBasicUserInfoUseCase() {



    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.deleteUserBasicInfo(params.id)
        emit(result)
    }
}