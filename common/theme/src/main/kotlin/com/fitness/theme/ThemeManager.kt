package com.fitness.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ThemeManager @Inject constructor() {
    val appTheme: StateFlow<AppTheme> = MutableStateFlow(AppTheme.Auth)
}