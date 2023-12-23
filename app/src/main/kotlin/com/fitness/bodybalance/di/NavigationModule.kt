package com.fitness.bodybalance.di

import com.fitness.authentication.di.AuthEntryModule
import com.fitness.onboard.di.OnboardEntryModule
import com.fitness.userprofile.di.UserProfileEntryModule
import com.fitness.welcome.di.WelcomeEntryModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(
    includes = [
        OnboardEntryModule::class,
        AuthEntryModule::class,
        UserProfileEntryModule::class,
        WelcomeEntryModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface NavigationModule