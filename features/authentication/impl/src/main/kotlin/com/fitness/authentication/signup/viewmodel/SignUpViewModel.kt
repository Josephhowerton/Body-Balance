package com.fitness.authentication.signup.viewmodel

import IntentViewModel
import android.util.Patterns
import com.fitness.domain.usecase.EmailPasswordCreateUseCase
import com.fitness.domain.usecase.GoogleCreateUseCase
import com.fitness.domain.usecase.PhoneNumberCreateUseCase
import com.fitness.domain.usecase.TwitterCreateUseCase
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import state.BaseViewState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CreateAccountViewModel @Inject constructor(
    val emailPasswordCreateUseCase: EmailPasswordCreateUseCase,
    val googleCreateUseCase: GoogleCreateUseCase,
    val phoneNumberCreateUseCase: PhoneNumberCreateUseCase,
    val twitterCreateUseCase: TwitterCreateUseCase,
    val createUserUseCase: CreateUserUseCase
): IntentViewModel<BaseViewState<CreateAccountState>, CreateAccountEvent>() {

    init {
        setState(BaseViewState.Data(CreateAccountState()))
    }

    override fun onTriggerEvent(event: CreateAccountEvent) {
        when(event){
            is CreateAccountEvent.EmailPasswordAuthData -> {
                onEmailPasswordAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    email = event.email,
                    password = event.password
                )
            }

            is CreateAccountEvent.GoogleAuthData -> {
                onGoogleAuth(
                    firstname = event.firstname,
                    lastname = event.lastname
                )
            }

            is CreateAccountEvent.PhoneAuthData -> {
                onPhoneAuth(
                    firstname = event.firstname,
                    lastname = event.lastname,
                    phone = event.phoneNumber
                )
            }

            is CreateAccountEvent.TwitterAuthData -> {
                onTwitterAuth(
                    firstname = event.firstname,
                    lastname = event.lastname
                )
            }
            else -> {
                setState(BaseViewState.Data(CreateAccountState()))
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.handleAuthFailure())
    }

    fun verifyCredentials(event: CreateAccountEvent) {
        when(event) {
            is CreateAccountEvent.EmailPasswordAuthData -> {
                val firstname = event.firstname
                val lastname = event.lastname
                val email = event.email
                val password = event.password
                setState(
                    BaseViewState.Data(
                        CreateAccountState(
                            !(firstname.isNullOrBlank() && lastname.isNullOrBlank()) &&
                                passwordVerification(password) &&
                                Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        )
                    )
                )
            }
            is CreateAccountEvent.PhoneAuthData -> {
                val firstname = event.firstname
                val lastname = event.lastname
                val phone = event.phoneNumber
                setState(
                    BaseViewState.Data(
                        CreateAccountState(
                            !(firstname.isNullOrBlank() && lastname.isNullOrBlank())
                                    && Patterns.PHONE.matcher(phone).matches()
                        )
                    )
                )

            }
            else -> {
                setState(BaseViewState.Data(CreateAccountState(false)))
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

    private fun onCreateUser(firebaseUser: UserDto) = safeLaunch{
        val params = CreateUserUseCase.Params(firebaseUser)
        call(createUserUseCase(params)) {
            setState(BaseViewState.Complete)
        }
    }

    private fun verify(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}