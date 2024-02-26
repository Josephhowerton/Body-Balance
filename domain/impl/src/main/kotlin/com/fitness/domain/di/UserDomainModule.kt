package com.fitness.domain.di

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.usecase.user.CreateBasicUserInfoUseCase
import com.fitness.domain.usecase.user.CreateBasicUserInfoUseCaseImpl
import com.fitness.domain.usecase.user.CreateUserBasicFitnessUseCase
import com.fitness.domain.usecase.user.CreateUserBasicFitnessUseCaseImpl
import com.fitness.domain.usecase.user.CreateUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.user.CreateUserBasicGoalsInfoUseCaseImpl
import com.fitness.domain.usecase.user.CreateUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.user.CreateUserBasicNutritionInfoUseCaseImpl
import com.fitness.domain.usecase.user.CreateUserUseCase
import com.fitness.domain.usecase.user.CreateUserUseCaseImpl
import com.fitness.domain.usecase.user.DeleteBasicUserInfoUseCase
import com.fitness.domain.usecase.user.DeleteBasicUserInfoUseCaseImpl
import com.fitness.domain.usecase.user.DeleteUserBasicFitnessUseCase
import com.fitness.domain.usecase.user.DeleteUserBasicFitnessUseCaseImpl
import com.fitness.domain.usecase.user.DeleteUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.user.DeleteUserBasicGoalsInfoUseCaseImpl
import com.fitness.domain.usecase.user.DeleteUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.user.DeleteUserBasicNutritionInfoUseCaseImpl
import com.fitness.domain.usecase.user.DeleteUserUseCase
import com.fitness.domain.usecase.user.DeleteUserUseCaseImpl
import com.fitness.domain.usecase.user.GetBasicUserInfoUseCase
import com.fitness.domain.usecase.user.GetBasicUserInfoUseCaseImpl
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCaseImpl
import com.fitness.domain.usecase.user.GetCurrentUserUseCase
import com.fitness.domain.usecase.user.GetCurrentUserUseCaseImpl
import com.fitness.domain.usecase.user.GetUserBasicFitnessUseCase
import com.fitness.domain.usecase.user.GetUserBasicFitnessUseCaseImpl
import com.fitness.domain.usecase.user.GetUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.user.GetUserBasicGoalsInfoUseCaseImpl
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCase
import com.fitness.domain.usecase.user.GetUserBasicNutritionInfoUseCaseImpl
import com.fitness.domain.usecase.user.UpdateBasicFitnessInfoUseCase
import com.fitness.domain.usecase.user.UpdateBasicFitnessInfoUseCaseImpl
import com.fitness.domain.usecase.user.UpdateBasicGoalsInfoUseCase
import com.fitness.domain.usecase.user.UpdateBasicGoalsInfoUseCaseImpl
import com.fitness.domain.usecase.user.UpdateBasicNutritionInfoUseCase
import com.fitness.domain.usecase.user.UpdateBasicNutritionInfoUseCaseImpl
import com.fitness.domain.usecase.user.UpdateBasicUserInfoUseCase
import com.fitness.domain.usecase.user.UpdateBasicUserInfoUseCaseImpl
import com.fitness.domain.usecase.user.UpdateUserPreferencesUseCase
import com.fitness.domain.usecase.user.UpdateUserPreferencesUseCaseImpl
import com.fitness.domain.usecase.user.UpdateUserUseCase
import com.fitness.domain.usecase.user.UpdateUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UserDomainModule {
    @Provides
    @Singleton
    fun provideCreateBasicUserInfoUseCase(userRepository: UserRepository): CreateBasicUserInfoUseCase =
        CreateBasicUserInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserBasicFitnessUseCase(userRepository: UserRepository): CreateUserBasicFitnessUseCase =
        CreateUserBasicFitnessUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideCreateBasicUserGoalUseCase(userRepository: UserRepository): CreateUserBasicGoalsInfoUseCase =
        CreateUserBasicGoalsInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideCreateBasicUserNutritionUseCase(userRepository: UserRepository): CreateUserBasicNutritionInfoUseCase =
        CreateUserBasicNutritionInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserUseCase(userRepository: UserRepository): CreateUserUseCase =
        CreateUserUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserInfoUseCase(userRepository: UserRepository): DeleteBasicUserInfoUseCase =
        DeleteBasicUserInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideDeleteUserBasicFitnessUseCase(userRepository: UserRepository): DeleteUserBasicFitnessUseCase =
        DeleteUserBasicFitnessUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserGoalUseCase(userRepository: UserRepository): DeleteUserBasicGoalsInfoUseCase =
        DeleteUserBasicGoalsInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideDeleteBasicUserNutritionUseCase(userRepository: UserRepository): DeleteUserBasicNutritionInfoUseCase =
        DeleteUserBasicNutritionInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(userRepository: UserRepository): DeleteUserUseCase =
        DeleteUserUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserInfoUseCase(userRepository: UserRepository): GetBasicUserInfoUseCase =
        GetBasicUserInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserIdUseCase(userRepository: UserRepository): GetCurrentUserIdUseCase =
        GetCurrentUserIdUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(userRepository: UserRepository): GetCurrentUserUseCase =
        GetCurrentUserUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetUserBasicFitnessUseCase(userRepository: UserRepository): GetUserBasicFitnessUseCase =
        GetUserBasicFitnessUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserGoalUseCase(userRepository: UserRepository): GetUserBasicGoalsInfoUseCase =
        GetUserBasicGoalsInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideGetBasicUserNutritionUseCase(userRepository: UserRepository): GetUserBasicNutritionInfoUseCase =
        GetUserBasicNutritionInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserInfoUseCase(userRepository: UserRepository): UpdateBasicUserInfoUseCase =
        UpdateBasicUserInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserBasicFitnessUseCase(userRepository: UserRepository): UpdateBasicFitnessInfoUseCase =
        UpdateBasicFitnessInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserGoalUseCase(userRepository: UserRepository): UpdateBasicGoalsInfoUseCase =
        UpdateBasicGoalsInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateBasicUserNutritionUseCase(userRepository: UserRepository): UpdateBasicNutritionInfoUseCase =
        UpdateBasicNutritionInfoUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserUseCase(userRepository: UserRepository): UpdateUserUseCase =
        UpdateUserUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserPreferencesUseCase(userRepository: UserRepository): UpdateUserPreferencesUseCase =
        UpdateUserPreferencesUseCaseImpl(userRepository)
}