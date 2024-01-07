package com.fitness.data.di

import com.fitness.data.repository.auth.AuthRepositoryImpl
import com.fitness.data.repository.user.UserRepositoryImpl
import com.fitness.data.repository.auth.AuthRepository
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
    fun provideEdamamFoodRepository(): EdamamFoodRepository = EdamamFoodRepositoryImpl()

    @Provides
    @Singleton
    fun provideEdamamNutritionRepository(): EdamamNutritionRepository = EdamamNutritionRepositoryImpl()

    @Provides
    @Singleton
    fun provideEdamamRecipeRepository(): EdamamRecipeRepository = EdamamRecipeRepositoryImpl()

}