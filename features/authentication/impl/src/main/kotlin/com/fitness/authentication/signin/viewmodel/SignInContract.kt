package com.fitness.authentication.signin.viewmodel

import com.fitness.authentication.util.AuthMethod
import auth.PhoneAuthState
import extensions.TextFieldState

data class SignInState(
    var authMethod: AuthMethod = AuthMethod.NONE,
    var emailState: TextFieldState = TextFieldState.PENDING,
    var phoneState: TextFieldState = TextFieldState.PENDING,
    var passwordState: TextFieldState = TextFieldState.PENDING,
    var codeState: TextFieldState = TextFieldState.PENDING,
    var emailErrorMessage: Int? = null,
    var phoneErrorMessage: Int? = null,
    var passwordErrorMessage: Int? = null,
    var codeErrorMessage: Int? = null,
    var phoneAuthState: PhoneAuthState = PhoneAuthState.Idle,
    var isAuthenticated: Boolean = false
)

sealed class SignInEvent {
    data class EmailPasswordAuthentication(val email:String, val password:String): SignInEvent()
    data class PhoneAuthentication(val phoneNumber: String): SignInEvent()
    data class VerifyPhoneAuthentication(val verificationId: String, val code: String) : SignInEvent()
    data class SelectAuthMethod(val method: AuthMethod): SignInEvent()
}