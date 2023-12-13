package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import usecase.LocalUseCase
import javax.inject.Inject

/*
TODO("update implementation")
 */
class ReadUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : LocalUseCase<ReadUserUseCase.Params, User>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<User>.execute(params: Params) {
    }
}