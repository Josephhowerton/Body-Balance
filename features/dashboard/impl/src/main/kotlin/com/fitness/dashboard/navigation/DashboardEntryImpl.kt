package com.fitness.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.fitness.resources.R
import androidx.navigation.NavHostController
import com.fitness.component.screens.MessageScreen
import com.fitness.dashboard.DashboardEntry
import com.fitness.navigation.Destinations
import javax.inject.Inject

class DashboardEntryImpl @Inject constructor() : DashboardEntry() {

    companion object {}


    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        MessageScreen(message = R.string.code_verification)
    }
}