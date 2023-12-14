package com.fitness.bodybalance.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitness.bodybalance.AppViewModel
import com.fitness.bodybalance.di.BodyBalanceApplication
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{ true }
        setContent {
            var showMainScreen by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = Unit) {
                delay(2000)
                splashScreen.setKeepOnScreenCondition{ false }
                showMainScreen = true
            }

            if (showMainScreen) {
                val viewModel: AppViewModel = viewModel()
                AppContent(
                    authState = viewModel.authState.collectAsState(),
                    appTheme = viewModel.appTheme.collectAsState(),
                    appProvider = (application as BodyBalanceApplication).appProvider
                )
            }
        }
    }
}