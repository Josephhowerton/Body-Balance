package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateBasicUserInfoUseCase @Inject constructor(val userRepository: UserRepository)
    : DataStateUseCase<CreateBasicUserInfoUseCase.Params, Unit>() {
    data class Params(
        val age: Int,
        val gender: String,
        val height: Double,
        val weight: Double,
        val dietaryPreferences: List<String>,
        val healthConcerns: List<String>
    )

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        TODO("Not yet implemented")
    }
}
