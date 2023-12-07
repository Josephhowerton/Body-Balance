package com.fitness.onboard.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.onboard.WelcomeEntry
import com.fitness.onboard.navigation.WelcomeEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface WelcomeEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(WelcomeEntry::class)
    fun welcomeEntry(entry: WelcomeEntryImpl) : FeatureEntry
}