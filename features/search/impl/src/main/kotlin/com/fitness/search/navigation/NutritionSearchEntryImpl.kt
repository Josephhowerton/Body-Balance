package com.fitness.search.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.navigation.Destinations
import com.fitness.search.NutritionSearchEntry
import com.fitness.search.nutrition.view.NutritionSearch
import com.fitness.search.nutrition.viewmodel.NutritionSearchViewModel
import extensions.cast
import javax.inject.Inject

class NutritionSearchEntryImpl @Inject constructor(): NutritionSearchEntry {
    override val featureRoute: String get() = "nutrition-search"

    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val viewModel: NutritionSearchViewModel = hiltViewModel()
        NutritionSearch(
            state = viewModel.uiState.cast(),
            onPopBack = { navController.popBackStack() },
            onTriggerEvent = { viewModel.onTriggerEvent(it) }
        )
    }
}