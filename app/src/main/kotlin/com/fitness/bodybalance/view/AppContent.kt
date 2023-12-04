package com.fitness.bodybalance.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fitness.bodybalance.di.AppProvider
import com.fitness.bodybalance.di.LocalAppProvider
import com.fitness.bodybalance.ui.theme.BodyBalanceTheme

@Composable
fun AppContent(appProvider: AppProvider) {
    BodyBalanceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CompositionLocalProvider(LocalAppProvider provides appProvider) {

                val navController = rememberNavController()
            }
        }
    }
}