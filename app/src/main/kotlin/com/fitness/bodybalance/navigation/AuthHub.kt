package com.fitness.bodybalance.navigation

import SignInEntry
import SignOutEntry
import SignUpEntry
import WelcomeEntry
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fitness.bodybalance.di.LocalAppProvider
import find

@Composable
fun AuthHub(navController: NavHostController) {
    OnboardingNavigation(navController)
}
@Composable
private fun OnboardingNavigation(navController: NavHostController){
    val destination = LocalAppProvider.current.destinations

    val signUp = destination.find<SignUpEntry>()
    val signIn = destination.find<SignInEntry>()
    val welcome = destination.find<WelcomeEntry>()


    NavHost(navController = navController, startDestination = welcome.featureRoute) {

        with(signIn) {
            navigation(navController, destination)
        }

        with(signUp) {
            navigation(navController, destination)
        }
    }
}