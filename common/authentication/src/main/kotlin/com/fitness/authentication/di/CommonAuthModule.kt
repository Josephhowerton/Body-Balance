package com.fitness.authentication.di

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationManagerImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonAuthModule {

    @Provides
    @Singleton
    fun provideAuthenticationManager(firebaseAuth: FirebaseAuth): AuthenticationManager = AuthenticationManagerImpl(firebaseAuth)
}