package com.fitness.authentication.signin.viewmodel

import auth.PhoneVerificationError
import auth.formatPhoneNumberToE164
import auth.toAuthFailure
import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.authentication.util.AuthStateHolder
import com.fitness.resources.R
import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.updatePhoneState
import com.fitness.authentication.util.verifyEmail
import com.fitness.authentication.util.verifyPassword
import com.fitness.authentication.util.verifyPhone
import com.fitness.data.PhoneAuthState
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCase
import com.fitness.domain.usecase.auth.FacebookSignInUseCase
import com.fitness.domain.usecase.auth.GoogleSignInUseCase
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCase
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCase
import com.fitness.domain.usecase.auth.XLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.TextFieldState
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val sendVerificationCodeUseCase: SendVerificationCodeUseCase,
    private val verificationCodeUseCase: VerifyPhoneNumberUseCase,
    private val emailPasswordSignInUseCase: EmailPasswordSignInUseCase,
    private val facebookSignInUseCase: FacebookSignInUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val xLoginUseCase: XLoginUseCase,
    private val authManager: AuthenticationManager
) : IntentViewModel<BaseViewState<SignInState>, SignInEvent>() {


    init {
        setState(BaseViewState.Data(SignInState()))
    }

    override fun onTriggerEvent(event: SignInEvent) {
        when(event){
            is SignInEvent.EmailPasswordAuthentication -> {
                verifyEmailAuthenticationData(event)
            }

            is SignInEvent.PhoneAuthentication -> {
                verifyPhoneAuthenticationData(event)
            }

            is SignInEvent.VerifyPhoneAuthentication -> {
                onVerifyPhoneNumber(event)
            }

            is SignInEvent.SelectAuthMethod ->{
                onSelectAuthMethod(event)
            }

            is SignInEvent.GoogleAuthentication -> {
                onGoogleAuthentication()
            }

            is SignInEvent.FacebookAuthentication -> {
                onFacebookAuthentication()
            }

            is SignInEvent.XAuthentication -> {
                onXAuthentication()
            }

            else -> {}
        }
    }

    override fun handleError(exception: Throwable) {
        if(exception is PhoneVerificationError){
            onResetVerifyPhoneNumber()
        }else{
            super.handleError(exception.toAuthFailure())
        }
    }
    private fun verifyEmailAuthenticationData(event: SignInEvent.EmailPasswordAuthentication) {
        val (emailState, emailError) = verifyEmail(event.email)
        val (passwordState, passwordError) = verifyPassword(event.password)

        if (isVerified(emailError, passwordError)){
            onEmailPasswordAuthentication(email = event.email, password = event.password)
        }
        else {
            onEmailAuthCredentialsError(
                emailState=emailState,
                passwordState=passwordState,
                emailError=emailError,
                passwordError=passwordError
            )
        }
    }

    private fun verifyPhoneAuthenticationData(event: SignInEvent.PhoneAuthentication) {
        val formattedPhoneNumber = formatPhoneNumberToE164(event.phoneNumber, "US")
        val (phoneState, phoneError) = verifyPhone(formattedPhoneNumber)
        if (formattedPhoneNumber != null && isVerified(phoneError)){
            onPhoneAuthentication(phone=formattedPhoneNumber)
        }
        else {
            onPhoneAuthCredentialsError(
                phoneState=phoneState,
                phoneError=phoneError,
            )
        }
    }

    private fun onEmailAuthCredentialsError(
        emailState: TextFieldState,
        passwordState: TextFieldState,
        emailError: Int,
        passwordError: Int
    ) {
        setState(
            BaseViewState.Data(
                SignInState(
                    emailState = emailState,
                    passwordState = passwordState,
                    emailErrorMessage = emailError,
                    passwordErrorMessage = passwordError
                )
            )
        )
    }

    private fun onPhoneAuthCredentialsError(
        phoneState: TextFieldState,
        phoneError: Int
    ) {
        setState(
            BaseViewState.Data(
                SignInState(
                    phoneState = phoneState,
                    phoneErrorMessage = phoneError
                )
            )
        )
    }

    private fun onSelectAuthMethod(event: SignInEvent.SelectAuthMethod) {
        setState(
            BaseViewState.Data(
                SignInState(authMethod = event.method)
            )
        )
    }

    private fun onEmailPasswordAuthentication(email: String, password:String) = safeLaunch {
        execute(emailPasswordSignInUseCase(EmailPasswordSignInUseCase.Params(email, password))) {
            setState(BaseViewState.Data(SignInState(isAuthenticated = true)))
        }
    }

    private fun onPhoneAuthentication(phone: String) = safeLaunch {
        updatePhoneState(phoneNumber = phone)
        execute(sendVerificationCodeUseCase(SendVerificationCodeUseCase.Params(phone))) {
            setState(BaseViewState.Data(SignInState(phoneAuthState = it)))
        }
    }

    private fun onVerifyPhoneNumber(event: SignInEvent.VerifyPhoneAuthentication) = safeLaunch {
        updatePhoneState(verificationId = event.verificationId)
        execute(verificationCodeUseCase(VerifyPhoneNumberUseCase.Params(verificationId=AuthStateHolder.getState().verificationId, code=event.code))){
            authManager.update(AuthenticationState.Authenticated)
        }
    }

    private fun onResetVerifyPhoneNumber() = safeLaunch {
        setState(
            BaseViewState.Data(
                SignInState(
                    phoneAuthState = PhoneAuthState.CodeSent(AuthStateHolder.getState().verificationId),
                    codeState = TextFieldState.ERROR,
                    codeErrorMessage = R.string.error_invalid_code
                )
            )
        )
    }
    private fun onGoogleAuthentication() = safeLaunch {
        execute(googleSignInUseCase(GoogleSignInUseCase.Params)) {
            setState(BaseViewState.Data(SignInState(isAuthenticated = true)))
        }
    }

    private fun onFacebookAuthentication() = safeLaunch {
        execute(facebookSignInUseCase(FacebookSignInUseCase.Params)) {
            setState(BaseViewState.Data(SignInState(isAuthenticated = true)))
        }
    }

    private fun onXAuthentication() = safeLaunch {
        execute(xLoginUseCase(XLoginUseCase.Params)) {
            authManager.update(AuthenticationState.Authenticated)
        }
    }
}