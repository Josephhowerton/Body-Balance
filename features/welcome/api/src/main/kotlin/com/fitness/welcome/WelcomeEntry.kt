package com.fitness.welcome

import com.fitness.navigation.ComposableFeatureEntry

abstract class WelcomeEntry: ComposableFeatureEntry {
    override val featureRoute: String get() = "welcome"
}