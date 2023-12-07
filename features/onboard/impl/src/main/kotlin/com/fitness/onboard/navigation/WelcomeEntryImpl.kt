package com.fitness.onboard.navigation

import com.fitness.navigation.Destinations
import com.fitness.onboard.WelcomeEntry
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import javax.inject.Inject

class WelcomeEntryImpl @Inject constructor(): WelcomeEntry() {
    @Composable
    override fun Composable(navController: NavHostController, destinations: Destinations, backStackEntry: NavBackStackEntry) {
    }
}