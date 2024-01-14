package com.fitness.recipebuilder.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.navigation.Destinations
import com.fitness.recipebuilder.RecipeBuilderEntry
import com.fitness.recipebuilder.view.RecipeBuilder
import com.fitness.recipebuilder.viewmodel.RecipeBuilderViewModel
import extensions.cast
import javax.inject.Inject

class RecipeBuilderEntryImpl @Inject constructor(): RecipeBuilderEntry {
    override val featureRoute: String get() = "nutrition-search"

    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val viewModel: RecipeBuilderViewModel = hiltViewModel()
        RecipeBuilder(
            state = viewModel.uiState.cast(),
            onPopBack = { navController.popBackStack() },
            onTriggerEvent = { viewModel.onTriggerEvent(it) }
        )
    }
}