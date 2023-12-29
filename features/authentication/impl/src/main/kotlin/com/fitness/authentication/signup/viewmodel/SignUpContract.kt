package com.fitness.authentication.signup.viewmodel

import com.fitness.authentication.util.AuthMethod
import com.fitness.data.PhoneAuthState
import extensions.TextFieldState

data class SignUpState(
    var authMethod: AuthMethod = AuthMethod.NONE,
    var phoneAuthState: PhoneAuthState = PhoneAuthState.Idle,
    var firstnameState: TextFieldState = TextFieldState.PENDING,
    var lastnameState: TextFieldState = TextFieldState.PENDING,
    var emailState: TextFieldState = TextFieldState.PENDING,
    var phoneState: TextFieldState = TextFieldState.PENDING,
    var passwordState: TextFieldState = TextFieldState.PENDING,
    var codeState: TextFieldState = TextFieldState.PENDING,
    var firstnameErrorMessage: Int? = null,
    var lastnameErrorMessage: Int? = null,
    var emailErrorMessage: Int? = null,
    var phoneErrorMessage: Int? = null,
    var passwordErrorMessage: Int? = null,
    var codeErrorMessage: Int? = null,
    var isSignUpCompleted: Boolean = false
)

sealed class SignUpEvent {
    data class EmailPasswordAuthData(
        val firstname: String,
        val lastname: String,
        val email: String,
        val password: String
    ) : SignUpEvent()

    data class PhoneAuthentication(
        val firstname: String,
        val lastname: String,
        val phoneNumber: String
    ) : SignUpEvent()

    data class VerifyPhoneAuthentication(val verificationId: String, val code: String) : SignUpEvent()
    data class SelectAuthMethod(val method: AuthMethod) : SignUpEvent()
    data class ThirdPartyAuthError(val error: Throwable) : SignUpEvent()
    object GoogleAuthentication : SignUpEvent()
    object FacebookAuthentication : SignUpEvent()
    object XAuthentication : SignUpEvent()
}