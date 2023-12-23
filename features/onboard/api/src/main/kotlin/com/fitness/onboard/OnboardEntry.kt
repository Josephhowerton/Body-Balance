package com.fitness.onboard

import com.fitness.navigation.AggregateFeatureEntry

abstract class OnboardEntry : AggregateFeatureEntry {
    override val featureRoute: String get() = "onboard"
}