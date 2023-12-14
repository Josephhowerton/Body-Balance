package com.fitness.authentication.di

import com.fitness.authentication.AuthEntry
import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.authentication.navigation.AuthEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(AuthEntry::class)
    fun authEntry(entry: AuthEntryImpl) : FeatureEntry

}