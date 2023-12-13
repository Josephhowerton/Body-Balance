package com.fitness.authentication.signin.viewmodel

import IntentViewModel
import state.BaseViewState
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val emailPasswordLoginUseCase: EmailPasswordLoginUseCase,
    val googleLoginUseCase: GoogleLoginUseCase,
    val phoneNumberLoginUseCase: PhoneNumberLoginUseCase,
    val twitterLoginUseCase: TwitterLoginUseCase
) : IntentViewModel<BaseViewState<LoginState>, LoginEvent>() {


    init {
        setState(BaseViewState.Data(LoginState()))
    }

    override fun onTriggerEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.EmailPasswordAuthData -> {
                onEmailPasswordAuth(email = event.email, password = event.password)
            }

            is LoginEvent.GoogleAuthData -> {
                onGoogleAuth(token = event.token)
            }

            is LoginEvent.PhoneAuthData -> {
                onPhoneAuth(phone = event.phoneNumber)
            }

            is LoginEvent.TwitterAuthData -> {
                onTwitterAuth(token = event.token)
            }
            is LoginEvent.Reset -> {
                setState(BaseViewState.Data(LoginState()))
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.handleAuthFailure())
    }

    fun verifyCredentials(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailPasswordAuthData -> {
                val email = event.email
                val password = event.password
                setState(
                    BaseViewState.Data(
                        LoginState(passwordVerification(password) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    )
                )
            }
            is LoginEvent.PhoneAuthData -> {
                val phone = event.phoneNumber
                setState(
                    BaseViewState.Data(
                        LoginState(Patterns.PHONE.matcher(phone).matches())
                    )
                )
            }
            else -> {
                setState(BaseViewState.Data(LoginState(false)))
            }
        }
    }

    private fun onEmailPasswordAuth(email: String, password:String) = safeLaunch {
        val params = EmailPasswordLoginUseCase.Params(email, password)
        execute(emailPasswordLoginUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }

    private fun onGoogleAuth(token: String) = safeLaunch {
        val params = GoogleLoginUseCase.Params(token)
        execute(googleLoginUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }

    private fun onPhoneAuth(phone: String) = safeLaunch {
        val params = PhoneNumberLoginUseCase.Params(phone)
        execute(phoneNumberLoginUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }

    private fun onTwitterAuth(token: String) = safeLaunch {
        val params = TwitterLoginUseCase.Params(token)
        execute(twitterLoginUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }
}