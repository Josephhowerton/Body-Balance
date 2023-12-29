package com.fitness.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.authentication.AuthEntry
import com.fitness.dashboard.DashboardEntry
import com.fitness.navigation.Destinations
import com.fitness.navigation.find
import com.fitness.welcome.WelcomeEntry
import com.fitness.welcome.view.WelcomeScreen
import com.fitness.welcome.viewmodel.WelcomeViewModel
import extensions.cast
import javax.inject.Inject

class WelcomeEntryImpl @Inject constructor() : WelcomeEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val viewModel = hiltViewModel<WelcomeViewModel>()
        WelcomeScreen(
            state = viewModel.uiState.cast(),
            onComplete = { authenticated ->
                if(authenticated){
                    navigate(navController, destinations.find<DashboardEntry>().featureRoute)
                }else{
                    navigate(navController, destinations.find<AuthEntry>().featureRoute)
                }
            }
        )
    }

    private fun navigate(navController: NavHostController, destination: String){
        navController.navigate(destination) {
            popUpTo(featureRoute){
                inclusive = true
            }
        }
    }
}