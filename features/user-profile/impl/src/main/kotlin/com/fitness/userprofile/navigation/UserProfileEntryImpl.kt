package com.fitness.userprofile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.navigation.Destinations
import com.fitness.userprofile.UserProfileEntry
import javax.inject.Inject

class UserProfileEntryImpl @Inject constructor(): UserProfileEntry {
    @Composable
    override fun Composable(navController: NavHostController, destinations: Destinations, backStackEntry: NavBackStackEntry) {}
}