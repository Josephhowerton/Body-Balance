package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import usecase.LocalUseCase
import javax.inject.Inject

class UpdateUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : LocalUseCase<UpdateUserUseCase.Params, Unit>() {

    sealed class Params{
        data class UpdateEmail(val id: String, val email: String): Params()
        data class UpdateDisplayName(val id: String, val firstname: String, val lastname: String): Params()
        data class UpdatePhoneNumber(val id: String, val phone: String): Params()
    }

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
    }
}