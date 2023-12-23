package com.fitness.userprofile.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.userprofile.UserProfileEntry
import com.fitness.userprofile.navigation.UserProfileEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserProfileEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(UserProfileEntry::class)
    fun userProfileEntry(entry: UserProfileEntryImpl): FeatureEntry
}