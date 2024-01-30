package com.fitness.data.model.cache.user

import enums.SystemOfMeasurement

data class UserPreferencesCache(val systemOfMeasurement: SystemOfMeasurement = SystemOfMeasurement.METRIC)