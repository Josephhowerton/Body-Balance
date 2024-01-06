package com.fitness.onboard.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.navigation.Destinations
import com.fitness.navigation.find
import com.fitness.onboard.OnboardEntry
import com.fitness.onboard.onboard.basic.BasicInformationScreen
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationViewModel
import com.fitness.onboard.onboard.fitness.FitnessScreen
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessViewModel
import com.fitness.onboard.onboard.goal.view.GoalsScreen
import com.fitness.onboard.onboard.goal.viewmodel.GoalViewModel
import com.fitness.onboard.onboard.nutrition.NutritionScreen
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionViewModel
import com.fitness.onboard.onboard.welcome.WelcomeScreen
import com.fitness.signout.SignOutEntry
import extensions.cast
import javax.inject.Inject

class OnboardEntryImpl @Inject constructor() : OnboardEntry() {

    companion object{
        const val WELCOME = "welcome"
        const val BASIC_INFORMATION = "basic_information"
        const val FITNESS_LEVEL_ASSESSMENT = "fitness_level_assessment"
        const val NUTRITION_PREFERENCES = "nutritional_preferences"
        const val INITIAL_GOALS = "goals"
    }
    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) {
        val signOut = destinations.find<SignOutEntry>()
        navigation(startDestination = WELCOME, route = featureRoute) {
            composable(WELCOME){
                WelcomeScreen(onContinue = { navController.navigate(BASIC_INFORMATION)} )
            }
            
            composable(BASIC_INFORMATION){
                val viewmodel: BasicInformationViewModel = hiltViewModel()
                BasicInformationScreen(
                    stateFlow = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onComplete = { navController.navigate(FITNESS_LEVEL_ASSESSMENT) },
                    onForceSignOut = { navController.navigate(signOut.featureRoute) }
                )
            }

            composable(FITNESS_LEVEL_ASSESSMENT){
                val viewmodel: FitnessViewModel = hiltViewModel()
                FitnessScreen(
                    state = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onComplete = { navController.navigate(NUTRITION_PREFERENCES) },
                    onForceSignOut = { navController.navigate(signOut.featureRoute) }
                )
            }

            composable(NUTRITION_PREFERENCES){
                val viewmodel: NutritionViewModel = hiltViewModel()
                NutritionScreen(
                    state = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onComplete = { navController.navigate(INITIAL_GOALS) },
                    onForceSignOut = { navController.navigate(signOut.featureRoute) }
                )
            }

            composable(INITIAL_GOALS){
                val viewmodel: GoalViewModel = hiltViewModel()
                GoalsScreen(
                    state = viewmodel.uiState.cast(),
                    onTriggerEvent = { viewmodel.onTriggerEvent(it) },
                    onForceSignOut = { navController.navigate(signOut.featureRoute) }
                )
            }
        }
    }
}