package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import usecase.LocalUseCase
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : LocalUseCase<CreateUserUseCase.Params, Unit>() {

    data class Params(val userDto: User)

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
    }

}