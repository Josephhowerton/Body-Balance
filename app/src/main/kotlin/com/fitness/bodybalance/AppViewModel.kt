package com.fitness.bodybalance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {
    // LiveData to hold the current theme
    private val _currentTheme = MutableLiveData(AppTheme.Hub)
    val currentTheme: LiveData<AppTheme> = _currentTheme

    // Function to switch themes
    fun setTheme(theme: AppTheme) {
        _currentTheme.value = theme
    }
}