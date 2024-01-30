package com.fitness.domain.model.user

import enums.SystemOfMeasurement

data class UserPreferences(val systemOfMeasurement: SystemOfMeasurement = SystemOfMeasurement.METRIC)