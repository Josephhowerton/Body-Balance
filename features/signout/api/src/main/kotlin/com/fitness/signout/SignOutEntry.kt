package com.fitness.signout

import com.fitness.navigation.ComposableFeatureEntry

interface SignOutEntry: ComposableFeatureEntry {
    override val featureRoute: String get() = "sign-out"
}