package com.fitness.data.di

import android.app.Application
import androidx.room.Room
import com.fitness.data.cache.LocalCache
import com.fitness.data.cache.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalCacheModule {

    @Provides
    @Singleton
    fun provideLocalDB(application: Application): LocalCache =
        Room.databaseBuilder(application, LocalCache::class.java, "local-cache")
            .build()

    @Provides
    @Singleton
    fun provideRecipeDao(cache: LocalCache): RecipeDao = cache.recipeDao()

}