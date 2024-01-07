package com.fitness.search.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.search.NutritionSearchEntry
import com.fitness.search.navigation.NutritionSearchEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(NutritionSearchEntry::class)
    fun nutritionSearchEntry(entry: NutritionSearchEntryImpl): FeatureEntry
}