package com.fitness.onboard.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.navigation.Destinations
import com.fitness.onboard.OnboardEntry
import com.fitness.onboard.onboard.view.OnboardScreen
import com.fitness.onboard.onboard.viewmodel.OnboardViewModel
import extensions.cast
import javax.inject.Inject

class OnboardEntryImpl @Inject constructor() : OnboardEntry() {

    companion object{
        const val ONBOARD_ONE = "onboard1"
    }
    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) {
        navigation(startDestination = ONBOARD_ONE, route = featureRoute) {
            composable(ONBOARD_ONE){
                val viewmodel:OnboardViewModel = hiltViewModel()
                OnboardScreen(
                    state = viewmodel.uiState.cast(),
                    onErrorEvent = {}
                )
            }
        }
    }
}