package com.fitness.authentication.signup.viewmodel

import viewmodel.IntentViewModel
import android.util.Patterns
import com.fitness.authentication.util.passwordVerification
import com.fitness.domain.model.user.User
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
        when(event){
            is SignUpEvent.EmailPasswordAuthData -> {
                onEmailPasswordAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    email = event.email,
                    password = event.password
                )
            }

            is SignUpEvent.GoogleAuthData -> {
                onGoogleAuth(
                    firstname = event.firstname,
                    lastname = event.lastname
                )
            }

            is SignUpEvent.PhoneAuthData -> {
                onPhoneAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    phone = event.phoneNumber
                )
            }

            is SignUpEvent.TwitterAuthData -> {
                onTwitterAuth(
                    firstname = event.firstname,
                    lastname = event.lastname
                )
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
        when(event) {
            is SignUpEvent.EmailPasswordAuthData -> {
                val firstname = event.firstname
                val lastname = event.lastname
                val email = event.email
                val password = event.password
                setState(
                    BaseViewState.Data(
                        SignUpState(
                            !(firstname.isNullOrBlank() && lastname.isNullOrBlank()) &&
                                passwordVerification(password) &&
                                Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        )
                    )
                )
            }
            is SignUpEvent.PhoneAuthData -> {
                val firstname = event.firstname
                val lastname = event.lastname
                val phone = event.phoneNumber
                setState(
                    BaseViewState.Data(
                        SignUpState(
                            !(firstname.isNullOrBlank() && lastname.isNullOrBlank())
                                    && Patterns.PHONE.matcher(phone).matches()
                        )
                    )
                )

            }
            else -> {
                setState(BaseViewState.Data(SignUpState(false)))
            }
        }
    }

    private fun onEmailPasswordAuth(firstname: String, lastname: String, email: String, password:String) = safeLaunch {
        val params = EmailPasswordCreateUseCase.Params(firstname, lastname, email, password)
        execute(emailPasswordCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onGoogleAuth(firstname: String, lastname: String) = safeLaunch {
        val params = GoogleCreateUseCase.Params(firstname, lastname)
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

    private fun onTwitterAuth(firstname: String, lastname: String) = safeLaunch {
        val params = TwitterCreateUseCase.Params(firstname, lastname)
        execute(twitterCreateUseCase(params)) {
            onCreateUser(it)
        }
    }

    private fun onCreateUser(firebaseUser: User) = safeLaunch{
        val params = CreateUserUseCase.Params(firebaseUser)
        call(createUserUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }
}