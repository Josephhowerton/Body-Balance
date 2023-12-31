package com.fitness.onboard.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.navigation.Destinations
import com.fitness.onboard.OnboardEntry
import com.fitness.onboard.onboard.basic.view.BasicInformationScreen
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationViewModel
import com.fitness.onboard.onboard.fitness.view.FitnessScreen
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessViewModel
import com.fitness.onboard.onboard.welcome.WelcomeScreen
import extensions.cast
import javax.inject.Inject

class OnboardEntryImpl @Inject constructor() : OnboardEntry() {

    companion object{
        const val WELCOME = "welcome"
        const val BASIC_INFORMATION = "basic_information"
        const val FITNESS_LEVEL_ASSESSMENT = "basic_information"
        const val NUTRITION_PREFERENCES = "nutritional_preferences"
        const val INITIAL_GOALS = "nutritional_preferences"
        const val FEEDBACK = "feedback"
    }
    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) { 
        navigation(startDestination = WELCOME, route = featureRoute) {
            composable(WELCOME){
                WelcomeScreen(
                    onContinue = {navController.navigate(WELCOME)}
                )
            }
            
            composable(BASIC_INFORMATION){
                val viewmodel: BasicInformationViewModel = hiltViewModel()
                BasicInformationScreen(
                    state = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onComplete = { navController.navigate(FITNESS_LEVEL_ASSESSMENT) }
                )
            }

            composable(FITNESS_LEVEL_ASSESSMENT){
                val viewmodel: FitnessViewModel = hiltViewModel()
                FitnessScreen(
                    state = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onComplete = { navController.navigate(NUTRITION_PREFERENCES) }
                )
            }

            composable(NUTRITION_PREFERENCES){
                val viewmodel: BasicInformationViewModel = hiltViewModel()
            }

            composable(INITIAL_GOALS){
                val viewmodel: BasicInformationViewModel = hiltViewModel()
            }

            composable(FEEDBACK){
                val viewmodel: BasicInformationViewModel = hiltViewModel()
            }
        }
    }
}