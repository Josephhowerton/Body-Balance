package com.fitness.bodybalance.di

import androidx.compose.runtime.compositionLocalOf
import com.fitness.navigation.Destinations
import javax.inject.Inject

class AppProvider @Inject constructor(val destinations: Destinations)

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }