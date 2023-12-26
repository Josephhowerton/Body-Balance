package com.fitness.dashboard

import com.fitness.navigation.ComposableFeatureEntry

abstract class DashboardEntry: ComposableFeatureEntry {
    override val featureRoute: String get() = "dashboard"
}