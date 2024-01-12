package com.fitness.data.di

import com.fitness.data.cache.RecipeDao
import com.fitness.data.network.EdamamAutoCompleteService
import com.fitness.data.network.EdamamFoodService
import com.fitness.data.network.EdamamNutritionService
import com.fitness.data.network.EdamamRecipeService
import com.fitness.data.repository.auth.AuthRepositoryImpl
import com.fitness.data.repository.user.UserRepositoryImpl
import com.fitness.data.repository.auth.AuthRepository
import com.fitness.data.repository.edamam.EdamamAutoCompleteRepository
import com.fitness.data.repository.edamam.EdamamAutoCompleteRepositoryImpl
import com.fitness.data.repository.edamam.EdamamFoodRepository
import com.fitness.data.repository.edamam.EdamamFoodRepositoryImpl
import com.fitness.data.repository.edamam.EdamamNutritionRepository
import com.fitness.data.repository.edamam.EdamamNutritionRepositoryImpl
import com.fitness.data.repository.edamam.EdamamRecipeRepository
import com.fitness.data.repository.edamam.EdamamRecipeRepositoryImpl
import com.fitness.data.repository.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideUserRepository(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): UserRepository = UserRepositoryImpl(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideEdamamAutoCompleteRepository(service: EdamamAutoCompleteService): EdamamAutoCompleteRepository = EdamamAutoCompleteRepositoryImpl(service = service)

    @Provides
    @Singleton
    fun provideEdamamFoodRepository(service: EdamamFoodService): EdamamFoodRepository = EdamamFoodRepositoryImpl(service = service)

    @Provides
    @Singleton
    fun provideEdamamNutritionRepository(service: EdamamNutritionService): EdamamNutritionRepository = EdamamNutritionRepositoryImpl(service = service)

    @Provides
    @Singleton
    fun provideEdamamRecipeRepository(recipeDao: RecipeDao, service: EdamamRecipeService): EdamamRecipeRepository = EdamamRecipeRepositoryImpl(cache = recipeDao, service = service)

}