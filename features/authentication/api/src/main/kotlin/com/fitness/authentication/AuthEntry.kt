package com.fitness.authentication

import com.fitness.navigation.AggregateFeatureEntry

abstract class AuthEntry: AggregateFeatureEntry {
    override val featureRoute: String get() = "auth"
}