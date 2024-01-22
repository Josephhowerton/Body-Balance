package com.fitness.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.navigation.Destinations
import com.fitness.search.RecipeSearchEntry
import com.fitness.search.screens.confirmation.SaveAndConfirm
import com.fitness.search.screens.confirmation.SaveConfirmViewModel
import com.fitness.search.screens.recipe.NutritionSearch
import com.fitness.search.screens.date.PickDate
import com.fitness.search.screens.date.PickDateViewModel
import com.fitness.search.screens.time.PickTime
import com.fitness.search.screens.recipe.RecipeSearchViewModel
import com.fitness.search.screens.time.PickTimeViewModel
import extensions.cast
import javax.inject.Inject

class RecipeSearchEntryImpl @Inject constructor() : RecipeSearchEntry {

    override val featureRoute: String get() = "recipe-search"

   private companion object{
       private const val RECIPE: String = "search"
       private const val DATE: String = "pick-date"
       private const val TIME: String = "pick-time"
       private const val CONFIRMATION: String = "confirmation"

   }

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
        destinations: Destinations
    ) {
        navigation(startDestination = RECIPE, route = featureRoute) {
            composable(RECIPE) {
                val viewModel: RecipeSearchViewModel = hiltViewModel()
                NutritionSearch(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onPickDate = { navController.navigate(DATE) }
                )
            }

            composable(DATE) {
                val viewModel: PickDateViewModel = hiltViewModel()
                PickDate(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onPickTime = { navController.navigate(TIME) }
                )
            }

            composable(TIME) {
                val viewModel: PickTimeViewModel = hiltViewModel()
                PickTime(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onConfirmation = { navController.navigate(CONFIRMATION) }
                )
            }

            composable(CONFIRMATION) {
                val viewModel: SaveConfirmViewModel = hiltViewModel()
                SaveAndConfirm(
                    state = viewModel.uiState.cast(),
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onPopBack = { navController.popBackStack() },
                    onComplete = {
                        navController.navigate(RECIPE) {
                            popUpTo(featureRoute) { inclusive = true }
                        }
                    },
                )
            }
        }
    }
}