package com.fitness.domain.usecase.metrics

import com.fitness.analytics.calculateBMR
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.metrics.UserBodyMetrics
import com.fitness.domain.model.toUserBodyMetricsCache
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement
import enums.convertLength
import enums.convertMass
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserBodyMetricsFromUserInfoUseCaseImpl  @Inject constructor(private val repository: UserRepository) : CreateUserBodyMetricsFromUserInfoUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val basicInfo = params.info
        val unitSystem = params.unitSystem

        val massUnit = if(unitSystem == SystemOfMeasurement.METRIC) EMassUnit.KILOGRAM else EMassUnit.POUNDS
        val lengthUnit = if(unitSystem == SystemOfMeasurement.METRIC) ELengthUnit.CENTIMETER else ELengthUnit.INCHES

        val height = basicInfo.height
        val weight = basicInfo.weight
        val waist = basicInfo.waist
        val age = basicInfo.age

        val gender = basicInfo.gender

        val bmi = weight.div(height.times(height))

        val basal = calculateBMR(
            gender = gender,
            weightKg = convertMass(weight, massUnit, EMassUnit.KILOGRAM),
            heightCm = convertLength(height, lengthUnit, ELengthUnit.CENTIMETER),
            waistCm = convertLength(waist, lengthUnit, ELengthUnit.CENTIMETER),
            ageYears = age,
        )

        val bodyMetrics = UserBodyMetrics(userId = basicInfo.userId, bodyMassIndex = bmi, bodyFatPercentage = 0.0, basalMetabolicRate = basal)

        emit(repository.updateUserBodyMetrics(id = basicInfo.userId, metrics = bodyMetrics.toUserBodyMetricsCache()))
    }
}