package com.fitness.data.di

import android.content.Context
import com.fitness.data.network.EdamamAutoCompleteService
import com.fitness.data.network.EdamamFoodService
import com.fitness.data.network.EdamamNutritionService
import com.fitness.data.network.EdamamRecipeService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import network.createHttpLoggingInterceptor
import network.createMoshi
import network.createOkHttpClient
import network.createRetrofitWithMoshi
import network.nutrition.NutritionInterceptor
import network.nutrition.createNutritionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

private const val EDAMAM = "edamam-auto-complete"
@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = EDAMAM)
    fun provideEdamamBaseUrl(): String = "https://api.edamam.com/"


    @Provides
    @Singleton
    fun provideMoshi(): Moshi = createMoshi()

    @Provides
    @Singleton
    fun provideNutritionInterceptor(): NutritionInterceptor = createNutritionInterceptor()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = createHttpLoggingInterceptor(true)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        nutritionInterceptor: NutritionInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = createOkHttpClient(context = context, isCache = true, nutritionInterceptor, httpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideAutoCompleteService(
        @Named(value = EDAMAM) baseUrl: String,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): EdamamAutoCompleteService = createRetrofitWithMoshi(baseUrl = baseUrl, okHttpClient = okHttpClient, moshi = moshi)

    @Provides
    @Singleton
    fun provideFoodDatabaseService(
        @Named(value = EDAMAM) baseUrl: String,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): EdamamFoodService = createRetrofitWithMoshi(baseUrl = baseUrl, okHttpClient = okHttpClient, moshi = moshi)

    @Provides
    @Singleton
    fun provideNutritionService(
        @Named(value = EDAMAM) baseUrl: String,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): EdamamNutritionService = createRetrofitWithMoshi(baseUrl = baseUrl, okHttpClient = okHttpClient, moshi = moshi)

    @Provides
    @Singleton
    fun provideRecipeService(
        @Named(value = EDAMAM) baseUrl: String,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): EdamamRecipeService = createRetrofitWithMoshi(baseUrl = baseUrl, okHttpClient = okHttpClient, moshi = moshi)
}