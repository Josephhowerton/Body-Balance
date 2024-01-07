package com.fitness.domain.di

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.usecase.cache.CreateBasicUserInfoUseCase
import com.fitness.domain.usecase.cache.CreateUserBasicFitnessUseCase
import com.fitness.domain.usecase.cache.CreateUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.CreateUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.CreateUserUseCase
import com.fitness.domain.usecase.cache.DeleteBasicUserInfoUseCase
import com.fitness.domain.usecase.cache.DeleteUserBasicFitnessUseCase
import com.fitness.domain.usecase.cache.DeleteUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.DeleteUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.DeleteUserUseCase
import com.fitness.domain.usecase.cache.GetBasicUserInfoUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserUseCase
import com.fitness.domain.usecase.cache.GetUserBasicFitnessUseCase
import com.fitness.domain.usecase.cache.GetUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.GetUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.UpdateBasicFitnessInfoUseCase
import com.fitness.domain.usecase.cache.UpdateBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.UpdateBasicNutritionInfoUseCase
import com.fitness.domain.usecase.cache.UpdateBasicUserInfoUseCase
import com.fitness.domain.usecase.cache.UpdateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CacheDomainModule {
    @Provides
    @Singleton
    fun provideCreateBasicUserInfoUseCase(userRepository: UserRepository) = CreateBasicUserInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserBasicFitnessUseCase(userRepository: UserRepository) = CreateUserBasicFitnessUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateBasicUserGoalUseCase(userRepository: UserRepository) = CreateUserBasicGoalsInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateBasicUserNutritionUseCase(userRepository: UserRepository) = CreateUserBasicNutritionInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserUseCase(userRepository: UserRepository) = CreateUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserInfoUseCase(userRepository: UserRepository) = DeleteBasicUserInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteUserBasicFitnessUseCase(userRepository: UserRepository) = DeleteUserBasicFitnessUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserGoalUseCase(userRepository: UserRepository) = DeleteUserBasicGoalsInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserNutritionUseCase(userRepository: UserRepository) = DeleteUserBasicNutritionInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(userRepository: UserRepository) = DeleteUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserInfoUseCase(userRepository: UserRepository) = GetBasicUserInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserIdUseCase(userRepository: UserRepository) = GetCurrentUserIdUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(userRepository: UserRepository) = GetCurrentUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetUserBasicFitnessUseCase(userRepository: UserRepository) = GetUserBasicFitnessUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserGoalUseCase(userRepository: UserRepository) = GetUserBasicGoalsInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserNutritionUseCase(userRepository: UserRepository) = GetUserBasicNutritionInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserInfoUseCase(userRepository: UserRepository) = UpdateBasicUserInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserBasicFitnessUseCase(userRepository: UserRepository) = UpdateBasicFitnessInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserGoalUseCase(userRepository: UserRepository) = UpdateBasicGoalsInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserNutritionUseCase(userRepository: UserRepository) = UpdateBasicNutritionInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserUseCase(userRepository: UserRepository) = UpdateUserUseCase(userRepository)
}