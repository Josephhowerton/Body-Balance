package com.fitness.bodybalance.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import Destinations
import javax.inject.Singleton

@Module(includes = [])
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppProvider(destinations: Destinations): AppProvider = AppProvider(destinations)
}