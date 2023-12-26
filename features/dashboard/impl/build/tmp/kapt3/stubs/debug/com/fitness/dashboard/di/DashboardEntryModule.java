package com.fitness.dashboard.di;

import com.fitness.dashboard.DashboardEntry;
import com.fitness.dashboard.navigation.DashboardEntryImpl;
import com.fitness.navigation.FeatureEntry;
import com.fitness.navigation.FeatureEntryKey;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0006"}, d2 = {"Lcom/fitness/dashboard/di/DashboardEntryModule;", "", "dashboardEntry", "Lcom/fitness/navigation/FeatureEntry;", "entry", "Lcom/fitness/dashboard/navigation/DashboardEntryImpl;", "impl_debug"})
@dagger.Module
public abstract interface DashboardEntryModule {
    
    @org.jetbrains.annotations.NotNull
    @com.fitness.navigation.FeatureEntryKey(value = com.fitness.dashboard.DashboardEntry.class)
    @dagger.multibindings.IntoMap
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.fitness.navigation.FeatureEntry dashboardEntry(@org.jetbrains.annotations.NotNull
    com.fitness.dashboard.navigation.DashboardEntryImpl entry);
}