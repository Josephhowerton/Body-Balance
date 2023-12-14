package com.fitness.authentication.signin.viewmodel

import viewmodel.IntentViewModel
import android.util.Patterns
import com.fitness.authentication.util.passwordVerification
import com.fitness.domain.usecase.auth.EmailPasswordLoginUseCase
import com.fitness.domain.usecase.auth.GoogleLoginUseCase
import com.fitness.domain.usecase.auth.PhoneNumberLoginUseCase
import com.fitness.domain.usecase.auth.TwitterLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.handleAuthFailure
import state.BaseViewState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val emailPasswordLoginUseCase: EmailPasswordLoginUseCase,
    val googleLoginUseCase: GoogleLoginUseCase,
    val phoneNumberLoginUseCase: PhoneNumberLoginUseCase,
    val twitterLoginUseCase: TwitterLoginUseCase
) : IntentViewModel<BaseViewState<SignInState>, SignInEvent>() {


    init {
        setState(BaseViewState.Data(SignInState()))
    }

    override fun onTriggerEvent(event: SignInEvent) {
        when(event){
            is SignInEvent.EmailPasswordAuthData -> {
                onEmailPasswordAuth(email = event.email, password = event.password)
            }

            is SignInEvent.GoogleAuthData -> {
                onGoogleAuth(token = event.token)
            }

            is SignInEvent.PhoneAuthData -> {
                onPhoneAuth(phone = event.phoneNumber)
            }

            is SignInEvent.TwitterAuthData -> {
                onTwitterAuth(token = event.token)
            }
            is SignInEvent.Reset -> {
                setState(BaseViewState.Data(SignInState()))
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.handleAuthFailure())
    }

    fun verifyCredentials(event: SignInEvent) {
        when(event) {
            is SignInEvent.EmailPasswordAuthData -> {
                val email = event.email
                val password = event.password
                setState(
                    BaseViewState.Data(
                        SignInState(passwordVerification(password) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    )
                )
            }
            is SignInEvent.PhoneAuthData -> {
                val phone = event.phoneNumber
                setState(
                    BaseViewState.Data(
                        SignInState(Patterns.PHONE.matcher(phone).matches())
                    )
                )
            }
            else -> {
                setState(BaseViewState.Data(SignInState(false)))
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