package com.fitness.domain.usecase.metrics

import com.fitness.analytics.calculateCarbs
import com.fitness.analytics.calculateFat
import com.fitness.analytics.calculateFiber
import com.fitness.analytics.calculateProtein
import com.fitness.analytics.calculateTDEE
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.metrics.UserRecommendedMacros
import com.fitness.domain.model.toUserRecommendedMacrosCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserRecommendedMacrosUseCaseImpl  @Inject constructor(private val repository: UserRepository) : CreateUserRecommendedMacrosUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val metrics = params.bodyMetrics
        val level = params.level

        val tdee = calculateTDEE(metrics.basalMetabolicRate, level)
        val protein = calculateProtein(tdee)
        val fat = calculateFat(tdee)
        val carbs = calculateCarbs(tdee)
        val fiber = calculateFiber(tdee)
        val macros = UserRecommendedMacros(
            userId = metrics.userId,
            calories = tdee,
            totalDailyEnergyExpenditure = tdee,
            carbohydrates =  carbs,
            fat = fat,
            protein = protein,
            fiber = fiber
        ).toUserRecommendedMacrosCache()

        emit(repository.updateUserRecommendedMetrics(metrics.userId, macros))
    }
}