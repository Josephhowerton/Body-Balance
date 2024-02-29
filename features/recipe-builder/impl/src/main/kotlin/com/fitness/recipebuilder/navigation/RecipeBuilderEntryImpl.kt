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
import com.fitness.recipebuilder.screens.recipes.RecipeSelection
import com.fitness.recipebuilder.screens.recipes.RecipeSelectionViewModel
import com.fitness.recipebuilder.screens.search.IngredientSearch
import com.fitness.recipebuilder.screens.search.IngredientSearchViewModel
import com.fitness.recipebuilder.screens.time.PickTime
import com.fitness.recipebuilder.screens.time.PickTimeViewModel
import extensions.cast
import javax.inject.Inject

class RecipeBuilderEntryImpl @Inject constructor(): RecipeBuilderEntry {
    override val featureRoute: String get() = "recipe-builder"
    companion object{
        const val MY_RECIPES: String = "my-recipes"
        const val RECIPE_BUILDER: String = "recipe-build"
        const val INGREDIENTS: String = "search-ingredients"
        const val DATE: String = "pick-date"
        const val TIME: String = "pick-time"
        const val MEAL_TYPE: String = "pick-meal-type"
        const val CONFIRMATION: String = "confirmation"

    }

    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) {
        navigation(startDestination = MY_RECIPES, route = featureRoute) {

            composable(MY_RECIPES){
                val viewModel: RecipeSelectionViewModel = hiltViewModel()
                RecipeSelection(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onNavigate = { navController.navigate(it) }
                )
            }

            composable(RECIPE_BUILDER){
                val viewModel: RecipeBuilderViewModel = hiltViewModel()
                RecipeBuilder(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onIngredientSearch = {navController.navigate(INGREDIENTS)}
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
                    onConfirmation = { navController.navigate(MY_RECIPES) }
                )
            }

            composable(CONFIRMATION) {
                val viewModel: SaveConfirmViewModel = hiltViewModel()
                SaveAndConfirm(
                    state = viewModel.uiState.cast(),
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onPopBack = { navController.popBackStack() },
                    onComplete = {
                        navController.navigate(MY_RECIPES)
                    },
                )
            }

        }
    }
}