package com.fitness.search.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.search.SearchEntry
import com.fitness.search.navigation.SearchEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SearchEntry::class)
    fun searchEntry(entry: SearchEntryImpl): FeatureEntry

}