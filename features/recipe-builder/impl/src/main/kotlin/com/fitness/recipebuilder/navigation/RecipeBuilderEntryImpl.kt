package com.fitness.recipebuilder.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.navigation.Destinations
import com.fitness.recipebuilder.RecipeBuilderEntry
import com.fitness.recipebuilder.screens.builder.RecipeBuilder
import com.fitness.recipebuilder.screens.builder.RecipeBuilderViewModel
import com.fitness.recipebuilder.screens.confirmation.SaveAndConfirm
import com.fitness.recipebuilder.screens.confirmation.SaveConfirmViewModel
import com.fitness.recipebuilder.screens.date.PickDate
import com.fitness.recipebuilder.screens.date.PickDateViewModel
import com.fitness.recipebuilder.screens.search.IngredientSearch
import com.fitness.recipebuilder.screens.search.IngredientSearchViewModel
import com.fitness.recipebuilder.screens.time.PickTime
import com.fitness.recipebuilder.screens.time.PickTimeViewModel
import extensions.cast
import javax.inject.Inject

class RecipeBuilderEntryImpl @Inject constructor(): RecipeBuilderEntry {
    override val featureRoute: String get() = "recipe-builder"
    private companion object{
        private const val RECIPE_BUILDER: String = "recipe-builder"
        private const val INGREDIENTS: String = "ingredient-search"
        private const val DATE: String = "pick-date"
        private const val TIME: String = "pick-time"
        private const val MEAL_TYPE: String = "pick-meal-type"
        private const val CONFIRMATION: String = "confirmation"

    }

    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) {
        navigation(startDestination = RECIPE_BUILDER, route = featureRoute) {

            composable(RECIPE_BUILDER){
                val viewModel: RecipeBuilderViewModel = hiltViewModel()
                RecipeBuilder(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) }
                )
            }

            composable(INGREDIENTS){
                val viewModel: IngredientSearchViewModel = hiltViewModel()
                IngredientSearch(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) }
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
                    onConfirmation = { navController.navigate(MEAL_TYPE) }
                )
            }

            composable(MEAL_TYPE) {
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
                        navController.navigate(INGREDIENTS) {
                            popUpTo(featureRoute) { inclusive = true }
                        }
                    },
                )
            }

        }
    }
}