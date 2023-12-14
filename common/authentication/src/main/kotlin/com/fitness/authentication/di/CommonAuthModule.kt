package com.fitness.authentication.di

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthenticationManager(): AuthenticationManager = AuthenticationManagerImpl()
}