package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import usecase.LocalUseCase
import javax.inject.Inject

class DeleteUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : LocalUseCase<DeleteUserUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<Unit>.execute(params: Params) {

    }
}