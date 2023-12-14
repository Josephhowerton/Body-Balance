package com.fitness.theme.ui

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.fitness.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BodyBalanceTheme(
    appTheme: State<AppTheme> = MutableStateFlow(AppTheme.Auth).collectAsState(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = colorScheme(
        appTheme = appTheme,
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    )

    HandleSideEffects(
        colorScheme = colorScheme,
        darkTheme = darkTheme
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

private val mainDarkColorScheme = darkColorScheme(
    primary = primaryColorHub.copy(alpha = 0.8f), // Adjusted for better visibility on dark backgrounds
    secondary = secondaryColorHub.copy(alpha = 0.8f),
    tertiary = tertiaryColorHub.copy(alpha = 0.8f),
    background = Color(0xFF121212), // Typical dark theme background
    surface = Color(0xFF1E1E1E), // Slightly lighter than the background for contrast
    onPrimary = Color.White, // Text color on primary
    onSecondary = Color.White, // Text color on secondary
    onTertiary = Color.White, // Text color on tertiary
    onBackground = Color.White, // Text color on background
    onSurface = Color.White // Text color on surface
)

private val onboardingLightColorScheme = lightColorScheme(
    primary = primaryColorAuth,
    secondary = secondaryColorAuth,
    tertiary = tertiaryColorAuth
)

private val authLightColorScheme = lightColorScheme(
    primary = primaryColorAuth,
    secondary = secondaryColorAuth,
    tertiary = tertiaryColorAuth
)

private val hubLightColorScheme = lightColorScheme(
    primary = primaryColorHub,
    secondary = secondaryColorHub,
    tertiary = tertiaryColorHub
)

private val spokeLightColorScheme = lightColorScheme(
    primary = primaryColorSpoke,
    secondary = secondaryColorSpoke,
    tertiary = tertiaryColorSpoke
)

@Composable
fun colorScheme(
    appTheme: State<AppTheme>,
    darkTheme: Boolean,
    dynamicColor: Boolean
): ColorScheme {
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> mainDarkColorScheme
        else -> when (appTheme.value) { // Use the appTheme parameter to switch color schemes
            AppTheme.Onboarding -> onboardingLightColorScheme
            AppTheme.Auth -> authLightColorScheme
            AppTheme.Hub -> hubLightColorScheme
            AppTheme.Spoke -> spokeLightColorScheme
        }
    }
}

@Composable
fun HandleSideEffects(
    colorScheme: ColorScheme,
    darkTheme: Boolean
){
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            hideSystemUI(window)
        }
    }
}

fun hideSystemUI(window: Window) {

    //Hides the ugly action bar at the top
    //Hide the status bars

    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } else {
        window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars())
            hide(WindowInsets.Type.systemBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}