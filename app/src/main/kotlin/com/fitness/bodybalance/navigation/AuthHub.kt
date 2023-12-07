package com.fitness.bodybalance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fitness.authentication.AuthEntry
import com.fitness.bodybalance.di.LocalAppProvider
import com.fitness.navigation.find
import com.fitness.onboard.WelcomeEntry

@Composable
fun AuthHub(navController: NavHostController) {
    OnboardingNavigation(navController)
}
@Composable
private fun OnboardingNavigation(navController: NavHostController){
    val destinations = LocalAppProvider.current.destinations

    val welcomeEntry = destinations.find<WelcomeEntry>()
    val authEntry = destinations.find<AuthEntry>()

    NavHost(navController = navController, startDestination = welcomeEntry.featureRoute) {

        with(authEntry) {
            navigation(navController, destinations)
        }
    }
}