package com.fitness.bodybalance.di

import android.app.Application
import android.content.Context
import com.fitness.authentication.di.CommonAuthModule
import com.fitness.authentication.di.FeatureAuthModule
import com.fitness.navigation.Destinations
import com.fitness.theme.di.ThemeModule
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [CommonAuthModule::class, ThemeModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppProvider(destinations: Destinations): AppProvider = AppProvider(destinations)
}