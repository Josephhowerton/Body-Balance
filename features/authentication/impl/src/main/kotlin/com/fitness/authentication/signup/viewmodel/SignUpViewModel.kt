package com.fitness.authentication.signup.viewmodel

import com.fitness.authentication.util.verifyEmail
import com.fitness.authentication.util.verifyName
import com.fitness.authentication.util.verifyPassword
import com.fitness.authentication.util.verifyPhone
import com.fitness.domain.model.user.User
import viewmodel.IntentViewModel
import com.fitness.domain.usecase.auth.EmailPasswordCreateUseCase
import com.fitness.domain.usecase.auth.GoogleCreateUseCase
import com.fitness.domain.usecase.auth.PhoneNumberCreateUseCase
import com.fitness.domain.usecase.auth.TwitterCreateUseCase
import com.fitness.domain.usecase.cache.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.handleAuthFailure
import state.BaseViewState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val emailPasswordCreateUseCase: EmailPasswordCreateUseCase,
    val googleCreateUseCase: GoogleCreateUseCase,
    val phoneNumberCreateUseCase: PhoneNumberCreateUseCase,
    val twitterCreateUseCase: TwitterCreateUseCase,
    val createUserUseCase: CreateUserUseCase
): IntentViewModel<BaseViewState<SignUpState>, SignUpEvent>() {

    init {
        setState(BaseViewState.Data(SignUpState()))
    }

    override fun onTriggerEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailPasswordAuthData -> {
                onEmailPasswordAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    email = event.email,
                    password = event.password
                )
            }

            is SignUpEvent.GoogleAuthentication -> {
                onGoogleAuth()
            }

            is SignUpEvent.PhoneAuthentication -> {
                onPhoneAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    phone = event.phoneNumber
                )
            }

            is SignUpEvent.TwitterAuthentication -> {
                onTwitterAuth()
            }

            else -> {
                setState(BaseViewState.Data(SignUpState()))
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.handleAuthFailure())
    }

    fun verifyCredentials(event: SignUpEvent) {

        when (event) {

            is SignUpEvent.EmailPasswordAuthData -> {
                val (firstnameState, firstnameError) = verifyName(event.firstname)
                val (lastnameState, lastnameError) = verifyName(event.lastname)
                val (emailState, emailError) = verifyEmail(event.email)
                val (passwordState, passwordError) = verifyPassword(event.password)
                setState(
                    BaseViewState.Data(
                        SignUpState(
                            isFirstnameVerified = firstnameState,
                            isLastnameVerified = lastnameState,
                            isEmailVerified = emailState,
                            isPasswordVerified = passwordState,
                            firstnameErrorMessage = firstnameError,
                            lastnameErrorMessage = lastnameError,
                            emailErrorMessage = emailError,
                            passwordErrorMessage = passwordError
                        )
                    )
                )
            }
            is SignUpEvent.PhoneAuthentication -> {
                val (firstnameState, firstnameError) = verifyName(event.firstname)
                val (lastnameState, lastnameError) = verifyName(event.lastname)
                val (phoneState, phoneError) = verifyPhone(event.phoneNumber)

                setState(
                    BaseViewState.Data(
                        SignUpState(
                            isFirstnameVerified = firstnameState,
                            isLastnameVerified = lastnameState,
                            isPhoneVerified = phoneState,
                            firstnameErrorMessage = firstnameError,
                            lastnameErrorMessage = lastnameError,
                            phoneErrorMessage = phoneError
                        )
                    )
                )
            }
            is SignUpEvent.FirstNameChanged -> {
                val (firstnameState, firstnameError) = verifyName(event.firstname)
                setState(
                    BaseViewState.Data(
                        currentState<SignUpState>().copy(
                            isFirstnameVerified = firstnameState,
                            firstnameErrorMessage = firstnameError
                        )
                    )
                )
            }
            is SignUpEvent.LastNameChanged -> {
                val (lastnameState, lastnameError) = verifyName(event.lastname)
                setState(
                    BaseViewState.Data(
                        currentState<SignUpState>().copy(
                            isLastnameVerified = lastnameState,
                            lastnameErrorMessage = lastnameError
                        )
                    )
                )
            }
            is SignUpEvent.EmailChanged -> {
                val (emailState, emailError) = verifyEmail(event.email)
                setState(
                    BaseViewState.Data(
                        currentState<SignUpState>().copy(
                            isEmailVerified = emailState,
                            emailErrorMessage = emailError
                        )
                    )
                )
            }
            is SignUpEvent.PasswordChanged -> {
                val (passwordState, passwordError) = verifyPassword(event.password)
                setState(
                    BaseViewState.Data(
                        currentState<SignUpState>().copy(
                            isPasswordVerified = passwordState,
                            passwordErrorMessage = passwordError
                        )
                    )
                )
            }

            else -> {}
        }
    }


    private fun onEmailPasswordAuth(firstname: String, lastname: String, email: String, password:String) = safeLaunch {
        val params = EmailPasswordCreateUseCase.Params(firstname, lastname, email, password)
        execute(emailPasswordCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onGoogleAuth() = safeLaunch {
        val params = GoogleCreateUseCase.Params
        execute(googleCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onPhoneAuth(firstname: String, lastname: String, phone:String) = safeLaunch {
        val params = PhoneNumberCreateUseCase.Params(firstname, lastname, phone)
        execute(phoneNumberCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onTwitterAuth() = safeLaunch {
        val params = TwitterCreateUseCase.Params
        execute(twitterCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onCreateUser(firebaseUser: User) = safeLaunch{
        val params = CreateUserUseCase.Params(firebaseUser)
        call(createUserUseCase(params)) {
        }
    }
}