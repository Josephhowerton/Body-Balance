package com.fitness.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.navigation.Destinations
import com.fitness.search.NutritionSearchEntry
import com.fitness.search.nutrition.view.NutritionSearch

class NutritionSearchEntryImpl: NutritionSearchEntry {
    override val featureRoute: String get() = "nutrition-search"

    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        NutritionSearch()
    }
}