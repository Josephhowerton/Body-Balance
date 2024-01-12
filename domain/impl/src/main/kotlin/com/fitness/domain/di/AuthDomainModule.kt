package com.fitness.domain.di

import com.fitness.data.repository.auth.AuthRepository
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCase
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCaseImpl
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCase
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCaseImpl
import com.fitness.domain.usecase.auth.SendPasswordResetEmailUseCase
import com.fitness.domain.usecase.auth.SendPasswordResetEmailUseCaseImpl
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCase
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCaseImpl
import com.fitness.domain.usecase.auth.SignOutUseCase
import com.fitness.domain.usecase.auth.SignOutUseCaseImpl
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCase
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthDomainModule {

    @Provides
    @Singleton
    fun provideEmailPasswordSignInUseCase(authRepository: AuthRepository): EmailPasswordSignInUseCase =
        EmailPasswordSignInUseCaseImpl(authRepository)

    @Provides
    @Singleton
    fun provideEmailPasswordSignUpUseCase(authRepository: AuthRepository): EmailPasswordSignUpUseCase =
        EmailPasswordSignUpUseCaseImpl(authRepository)

    @Provides
    @Singleton
    fun provideSendPasswordResetEmailUseCase(authRepository: AuthRepository): SendPasswordResetEmailUseCase =
        SendPasswordResetEmailUseCaseImpl(authRepository)

    @Provides
    @Singleton
    fun provideSendVerificationCodeUseCase(authRepository: AuthRepository): SendVerificationCodeUseCase =
        SendVerificationCodeUseCaseImpl(authRepository)

    @Provides
    @Singleton
    fun provideSignOutUseCase(authRepository: AuthRepository): SignOutUseCase =
        SignOutUseCaseImpl(authRepository)

    @Provides
    @Singleton
    fun provideVerifyPhoneNumberUseCase(authRepository: AuthRepository): VerifyPhoneNumberUseCase =
        VerifyPhoneNumberUseCaseImpl(authRepository)

}