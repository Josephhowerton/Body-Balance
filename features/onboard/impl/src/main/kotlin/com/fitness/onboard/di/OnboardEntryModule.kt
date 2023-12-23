package com.fitness.onboard.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.onboard.OnboardEntry
import com.fitness.onboard.navigation.OnboardEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface OnboardEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(OnboardEntry::class)
    fun onboardEntry(entry: OnboardEntryImpl): FeatureEntry
}