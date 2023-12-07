package com.fitness.bodybalance.di

import android.app.Application
import android.content.Context
import com.fitness.authentication.AuthenticationManager
import com.fitness.authentication.FirebaseAuthenticationManager
import com.fitness.authentication.di.AuthEntryModule
import com.fitness.navigation.Destinations
import com.fitness.onboard.di.WelcomeEntryModule
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [
    WelcomeEntryModule::class,
    AuthEntryModule::class
])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppProvider(destinations: Destinations): AppProvider = AppProvider(destinations)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthManager(firebaseAuth: FirebaseAuth): AuthenticationManager = FirebaseAuthenticationManager(firebaseAuth)
}