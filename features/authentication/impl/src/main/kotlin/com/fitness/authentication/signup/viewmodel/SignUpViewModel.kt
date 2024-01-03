package com.fitness.authentication.signup.viewmodel


import auth.PhoneVerificationError
import auth.formatPhoneNumberToE164
import auth.toAuthFailure
import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.authentication.util.AuthStateHolder
import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.updatePhoneState
import com.fitness.authentication.util.verifyEmail
import com.fitness.authentication.util.verifyName
import com.fitness.authentication.util.verifyPassword
import com.fitness.authentication.util.verifyPhone
import com.fitness.data.PhoneAuthState
import com.fitness.data.model.domain.user.UserDomain
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCase
import com.fitness.domain.usecase.auth.FacebookSignUpUseCase
import com.fitness.domain.usecase.auth.GoogleSignUpUseCase
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCase
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCase
import com.fitness.domain.usecase.auth.XSignUpUseCase
import com.fitness.domain.usecase.cache.CreateUserUseCase
import com.fitness.resources.R
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.TextFieldState
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sendVerificationCodeUseCase: SendVerificationCodeUseCase,
    private val verificationCodeUseCase: VerifyPhoneNumberUseCase,
    private val emailPasswordSignUpUseCase: EmailPasswordSignUpUseCase,
    private val facebookSignUpUseCase: FacebookSignUpUseCase,
    private val googleSignUpUseCase: GoogleSignUpUseCase,
    private val xSignUpUseCase: XSignUpUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val authManager: AuthenticationManager
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

            is SignUpEvent.VerifyPhoneAuthentication -> {
                onVerifyPhoneNumber(event)
            }

            is SignUpEvent.SelectAuthMethod -> {
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
                handleError(event.error)
            }
        }
    }

    override fun handleError(exception: Throwable) {
        if (exception is PhoneVerificationError) {
            onResetVerifyPhoneNumber()
        } else {
            super.handleError(exception.toAuthFailure())
        }
    }

    private fun verifyEmailAuthenticationData(event: SignUpEvent.EmailPasswordAuthData) {
        val (firstnameState, firstnameError) = verifyName(event.firstname)
        val (lastnameState, lastnameError) = verifyName(event.lastname)
        val (emailState, emailError) = verifyEmail(event.email)
        val (passwordState, passwordError) = verifyPassword(event.password)

        if (isVerified(firstnameError, lastnameError, emailError, passwordError)) {
            onEmailPasswordAuthentication(
                event.firstname,
                event.lastname,
                event.email,
                event.password
            )
        } else {
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
        val formattedPhoneNumber = formatPhoneNumberToE164(event.phoneNumber, "US")

        if (formattedPhoneNumber != null && isVerified(firstnameError, lastnameError, phoneError)) {
            onPhoneAuthentication(event.firstname, event.lastname, formattedPhoneNumber)
        } else {
            val phoneStateError =
                if (formattedPhoneNumber == null) TextFieldState.ERROR else phoneState
            val phoneErrorMsg =
                if (formattedPhoneNumber == null) R.string.invalid_phone_number_format else phoneError

            onPhoneAuthCredentialsError(
                firstnameState,
                lastnameState,
                phoneStateError,
                firstnameError,
                lastnameError,
                phoneErrorMsg
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

    private fun onPhoneAuthentication(firstname: String, lastname: String, phone: String) {
        updatePhoneState(firstname = firstname, lastname = lastname, phoneNumber = phone)
        safeLaunch {
            execute(sendVerificationCodeUseCase(SendVerificationCodeUseCase.Params(phone))) {
                setState(
                    BaseViewState.Data(
                        SignUpState(phoneAuthState = it)
                    )
                )
            }
        }
    }

    private fun onVerifyPhoneNumber(event: SignUpEvent.VerifyPhoneAuthentication) = safeLaunch {
        updatePhoneState(verificationId = event.verificationId)
        execute(
            verificationCodeUseCase(
                VerifyPhoneNumberUseCase.Params(
                    verificationId = event.verificationId,
                    code = event.code
                )
            )
        ) {

            onCreateUser(it)
        }
    }

    private fun onResetVerifyPhoneNumber() = safeLaunch {
        setState(
            BaseViewState.Data(
                SignUpState(
                    phoneAuthState = PhoneAuthState.CodeSent(
                        AuthStateHolder.getState().verificationId
                    )
                )
            )
        )
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

    private fun onCreateUser(userDomain: UserDomain) = safeLaunch {
        val params = CreateUserUseCase.Params(userDomain)
        call(createUserUseCase(params)) {
            authManager.update(AuthenticationState.OnBoard)
        }
    }
}