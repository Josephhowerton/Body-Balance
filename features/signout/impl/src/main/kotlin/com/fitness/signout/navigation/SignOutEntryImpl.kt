package com.fitness.signout.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.fitness.navigation.Destinations
import com.fitness.signout.SignOutEntry
import com.fitness.signout.view.SignOut
import com.fitness.signout.viewmodel.SignOutViewModel
import javax.inject.Inject

class SignOutEntryImpl @Inject constructor(): SignOutEntry {
    @Composable
    override fun Composable(navController: NavHostController, destinations: Destinations, backStackEntry: NavBackStackEntry) {
        val viewModel: SignOutViewModel = hiltViewModel()
        SignOut(onTriggerEvent = { viewModel.onTriggerEvent(it) })
    }
}