package com.fitness.bodybalance.di

import com.fitness.authentication.di.AuthEntryModule
import com.fitness.onboard.di.WelcomeEntryModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [
    WelcomeEntryModule::class,
    AuthEntryModule::class
])
@InstallIn(SingletonComponent::class)
interface NavigationModule