package com.fitness.domain.usecase.cache

import com.fitness.data.extensions.toUserCache
import com.fitness.data.model.domain.user.UserDomain
import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<CreateUserUseCase.Params, Unit>() {

    data class Params(val userDomain: UserDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        params.userDomain.isTermAndPrivacyAccepted = true
        val result = userRepository.createUser(params.userDomain.toUserCache())
        emit(result)
    }
}