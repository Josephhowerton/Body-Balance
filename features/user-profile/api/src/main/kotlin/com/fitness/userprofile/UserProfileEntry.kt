package com.fitness.userprofile

import com.fitness.navigation.ComposableFeatureEntry

interface UserProfileEntry: ComposableFeatureEntry {
    override val featureRoute: String get() = "user-profile"
}