package com.fitness.authentication.signup.viewmodel

import auth.handleAuthFailure
import com.fitness.authentication.util.verifyEmail
import com.fitness.authentication.util.verifyName
import com.fitness.authentication.util.verifyPassword
import com.fitness.authentication.util.verifyPhone
import com.fitness.data.model.model.user.UserDomain
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCase
import com.fitness.domain.usecase.auth.GoogleSignUpUseCase
import com.fitness.domain.usecase.auth.PhoneNumberSignUpUseCase
import com.fitness.domain.usecase.auth.XSignUpUseCase
import com.fitness.domain.usecase.cache.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.TextFieldState
import com.fitness.authentication.util.isVerified
import com.fitness.domain.usecase.auth.FacebookSignUpUseCase
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val emailPasswordSignUpUseCase: EmailPasswordSignUpUseCase,
    val phoneNumberSignUpUseCase: PhoneNumberSignUpUseCase,
    val facebookSignUpUseCase: FacebookSignUpUseCase,
    val googleSignUpUseCase: GoogleSignUpUseCase,
    val xSignUpUseCase: XSignUpUseCase,
    val createUserUseCase: CreateUserUseCase
) : IntentViewModel<BaseViewState<SignUpState>, SignUpEvent>() {

    init {
        setState(BaseViewState.Data(SignUpState()))
    }

    override fun onTriggerEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailPasswordAuthData -> {
                verifyEmailAuthenticationData(event)
            }

            is SignUpEvent.PhoneAuthentication -> {
                verifyPhoneAuthenticationData(event)
            }

            is SignUpEvent.SelectAuthMethod ->{
                onSelectAuthMethod(event)
            }

            is SignUpEvent.GoogleAuthentication -> {
                onGoogleAuthentication()
            }

            is SignUpEvent.FacebookAuthentication -> {
                onFacebookAuthentication()
            }

            is SignUpEvent.XAuthentication -> {
                onXAuthentication()
            }

            is SignUpEvent.ThirdPartyAuthError -> {
                handleError(event.error.handleAuthFailure())
            }

            else -> {
                setState(BaseViewState.Data(SignUpState()))
            }
        }
    }

    private fun verifyEmailAuthenticationData(event: SignUpEvent.EmailPasswordAuthData) {
        val (firstnameState, firstnameError) = verifyName(event.firstname)
        val (lastnameState, lastnameError) = verifyName(event.lastname)
        val (emailState, emailError) = verifyEmail(event.email)
        val (passwordState, passwordError) = verifyPassword(event.password)

        if (isVerified(firstnameError, lastnameError, emailError, passwordError)){
            onEmailPasswordAuthentication(event.firstname, event.lastname, event.email, event.password)
        }
        else {
            onEmailAuthCredentialsError(
                firstnameState,
                lastnameState,
                emailState,
                passwordState,
                firstnameError,
                lastnameError,
                emailError,
                passwordError
            )
        }
    }

    private fun verifyPhoneAuthenticationData(event: SignUpEvent.PhoneAuthentication) {
        val (firstnameState, firstnameError) = verifyName(event.firstname)
        val (lastnameState, lastnameError) = verifyName(event.lastname)
        val (phoneState, phoneError) = verifyPhone(event.phoneNumber)
        if (isVerified(firstnameError, lastnameError, phoneError)){
            onPhoneAuthentication(event.firstname, event.lastname, event.phoneNumber)
        }
        else {
            onPhoneAuthCredentialsError(
                firstnameState,
                lastnameState,
                phoneState,
                firstnameError,
                lastnameError,
                phoneError,
            )
        }
    }

    private fun onEmailAuthCredentialsError(
        firstnameState: TextFieldState,
        lastnameState: TextFieldState,
        emailState: TextFieldState,
        passwordState: TextFieldState,
        firstnameError: Int,
        lastnameError: Int,
        emailError: Int,
        passwordError: Int
    ) {
        setState(
            BaseViewState.Data(
                SignUpState(
                    firstnameState = firstnameState,
                    lastnameState = lastnameState,
                    emailState = emailState,
                    passwordState = passwordState,
                    firstnameErrorMessage = firstnameError,
                    lastnameErrorMessage = lastnameError,
                    emailErrorMessage = emailError,
                    passwordErrorMessage = passwordError
                )
            )
        )
    }


    private fun onPhoneAuthCredentialsError(
        firstnameState: TextFieldState,
        lastnameState: TextFieldState,
        phoneState: TextFieldState,
        firstnameError: Int,
        lastnameError: Int,
        phoneError: Int
    ) {
        setState(
            BaseViewState.Data(
                SignUpState(
                    firstnameState = firstnameState,
                    lastnameState = lastnameState,
                    phoneState = phoneState,
                    firstnameErrorMessage = firstnameError,
                    lastnameErrorMessage = lastnameError,
                    phoneErrorMessage = phoneError
                )
            )
        )
    }

    private fun onEmailPasswordAuthentication(
        firstname: String,
        lastname: String,
        email: String,
        password: String
    ) = safeLaunch {
        val params = EmailPasswordSignUpUseCase.Params(firstname, lastname, email, password)
        execute(emailPasswordSignUpUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onPhoneAuthentication(firstname: String, lastname: String, phone: String) =
        safeLaunch {
            val params = PhoneNumberSignUpUseCase.Params(firstname, lastname, phone)
            execute(phoneNumberSignUpUseCase(params)) {
                onCreateUser(it)
            }
        }

    private fun onSelectAuthMethod(event: SignUpEvent.SelectAuthMethod) {
        setState(
            BaseViewState.Data(
                SignUpState(authMethod = event.method)
            )
        )
    }

    private fun onGoogleAuthentication() = safeLaunch {
        execute(googleSignUpUseCase(GoogleSignUpUseCase.Params)) {
            onCreateUser(it)
        }
    }

    private fun onFacebookAuthentication() = safeLaunch {
        execute(facebookSignUpUseCase(FacebookSignUpUseCase.Params)) {
            onCreateUser(it)
        }
    }

    private fun onXAuthentication() = safeLaunch {
        execute(xSignUpUseCase(XSignUpUseCase.Params)) {
            onCreateUser(it)
        }
    }

    private fun onCreateUser(firebaseUserDomain: UserDomain) = safeLaunch {
        val params = CreateUserUseCase.Params(firebaseUserDomain)
        call(createUserUseCase(params)) {
            setState(BaseViewState.Data(SignUpState(isSignUpCompleted = true)))
        }
    }
}