package com.fitness.welcome.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.welcome.WelcomeEntry
import com.fitness.welcome.navigation.WelcomeEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WelcomeEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(WelcomeEntry::class)
    fun welcomeEntry(entry: WelcomeEntryImpl): FeatureEntry
}