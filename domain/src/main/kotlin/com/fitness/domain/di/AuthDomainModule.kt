package com.fitness.domain.di

import com.fitness.data.repository.AuthRepository
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCase
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCase
import com.fitness.domain.usecase.auth.FacebookSignUpUseCase
import com.fitness.domain.usecase.auth.FacebookSignInUseCase
import com.fitness.domain.usecase.auth.GoogleSignUpUseCase
import com.fitness.domain.usecase.auth.GoogleSignInUseCase
import com.fitness.domain.usecase.auth.SendPasswordResetEmailUseCase
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCase
import com.fitness.domain.usecase.auth.SignOutUseCase
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCase
import com.fitness.domain.usecase.auth.XSignUpUseCase
import com.fitness.domain.usecase.auth.XSignInUseCase
import com.google.firebase.auth.FirebaseAuth
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
    fun provideEmailPasswordSignInUseCase(authRepository: AuthRepository) = EmailPasswordSignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideEmailPasswordSignUpUseCase(authRepository: AuthRepository) = EmailPasswordSignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideFacebookSignInUseCase() = FacebookSignInUseCase()

    @Provides
    @Singleton
    fun provideFacebookSignUpUseCase() = FacebookSignUpUseCase()

    @Provides
    @Singleton
    fun provideGoogleSignInUseCase(firebaseAuth: FirebaseAuth) = GoogleSignInUseCase(firebaseAuth)

    @Provides
    @Singleton
    fun provideGoogleSignUpUseCase(firebaseAuth: FirebaseAuth) = GoogleSignUpUseCase(firebaseAuth)

    @Provides
    @Singleton
    fun provideSendPasswordResetEmailUseCase(authRepository: AuthRepository) = SendPasswordResetEmailUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSendVerificationCodeUseCase(authRepository: AuthRepository) = SendVerificationCodeUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignOutUseCase(authRepository: AuthRepository) = SignOutUseCase(authRepository)

    @Provides
    @Singleton
    fun provideVerifyPhoneNumberUseCase(authRepository: AuthRepository) = VerifyPhoneNumberUseCase(authRepository)

    @Provides
    @Singleton
    fun provideXCreateUseCase() = XSignUpUseCase()

    @Provides
    @Singleton
    fun provideXLoginUseCase() = XSignInUseCase()

}