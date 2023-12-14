package com.fitness.theme.di

import com.fitness.theme.ThemeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ThemeModule {
    @Provides
    @Singleton
    fun provideThemeManager(): ThemeManager = ThemeManager()
}