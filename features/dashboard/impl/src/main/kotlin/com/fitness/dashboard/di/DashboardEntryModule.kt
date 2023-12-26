package com.fitness.dashboard.di

import com.fitness.dashboard.DashboardEntry
import com.fitness.dashboard.navigation.DashboardEntryImpl
import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DashboardEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(DashboardEntry::class)
    fun dashboardEntry(entry: DashboardEntryImpl) : FeatureEntry

}