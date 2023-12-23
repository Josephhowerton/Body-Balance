package com.fitness.authentication.signin.viewmodel

import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.signup.viewmodel.SignUpState
import viewmodel.IntentViewModel
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCase
import com.fitness.domain.usecase.auth.GoogleSignInUseCase
import com.fitness.domain.usecase.auth.PhoneNumberSignInUseCase
import com.fitness.domain.usecase.auth.XLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.verifyEmail
import com.fitness.authentication.util.verifyPassword
import com.fitness.authentication.util.verifyPhone
import com.fitness.domain.usecase.auth.FacebookSignInUseCase
import extensions.TextFieldState
import state.BaseViewState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val emailPasswordSignInUseCase: EmailPasswordSignInUseCase,
    val phoneNumberSignInUseCase: PhoneNumberSignInUseCase,
    val facebookSignInUseCase: FacebookSignInUseCase,
    val googleSignInUseCase: GoogleSignInUseCase,
    val xLoginUseCase: XLoginUseCase
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
        val (phoneState, phoneError) = verifyPhone(event.phoneNumber)
        if (isVerified(phoneError)){
            onPhoneAuthentication(phone=event.phoneNumber)
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
        execute(phoneNumberSignInUseCase(PhoneNumberSignInUseCase.Params(phone))) {
            setState(BaseViewState.Data(SignInState(isAuthenticated = true)))
        }
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
            setState(BaseViewState.Data(SignInState(isAuthenticated = true)))
        }
    }
}