package com.fitness.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.authentication.AuthEntry
import com.fitness.navigation.Destinations
import com.fitness.navigation.find
import com.fitness.welcome.WelcomeEntry
import com.fitness.welcome.view.WelcomeScreen
import javax.inject.Inject

class WelcomeEntryImpl @Inject constructor() : WelcomeEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        WelcomeScreen(
            onComplete = {
                navController.navigate(destinations.find<AuthEntry>().featureRoute) {
                    popUpTo(featureRoute){
                        inclusive = true
                    }
                }
            }
        )
    }
}