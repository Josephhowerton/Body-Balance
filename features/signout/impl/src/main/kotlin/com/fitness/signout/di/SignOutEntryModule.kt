package com.fitness.signout.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.signout.SignOutEntry
import com.fitness.signout.navigation.SignOutEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SignOutEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SignOutEntry::class)
    fun signOutEntry(entry: SignOutEntryImpl) : FeatureEntry

}