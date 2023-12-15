package com.fitness.authentication.signup.viewmodel

import extensions.TextFieldState

data class SignUpState(
    var isFirstnameVerified: TextFieldState = TextFieldState.PENDING,
    var isLastnameVerified: TextFieldState = TextFieldState.PENDING,
    var isEmailVerified: TextFieldState = TextFieldState.PENDING,
    var isPhoneVerified: TextFieldState = TextFieldState.PENDING,
    var isPasswordVerified: TextFieldState = TextFieldState.PENDING,
    var firstnameErrorMessage: Int? = null,
    var lastnameErrorMessage: Int? = null,
    var emailErrorMessage: Int? = null,
    var phoneErrorMessage: Int? = null,
    var passwordErrorMessage: Int? = null,
)

sealed class SignUpEvent {
    data class FirstNameChanged(val firstname: String) : SignUpEvent()
    data class LastNameChanged(val lastname: String) : SignUpEvent()
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class PhoneChanged(val phone: String) : SignUpEvent()
    data class EmailPasswordAuthData(val firstname: String, val lastname: String, val email: String, val password: String) : SignUpEvent()

    data class PhoneAuthentication(val firstname: String, val lastname: String, val phoneNumber: String) : SignUpEvent()
    object GoogleAuthentication : SignUpEvent()
    object TwitterAuthentication : SignUpEvent()
    object Reset : SignUpEvent()

    object TermsAndConditions: SignUpEvent()
    object PrivacyPolicy: SignUpEvent()
    object SignIn: SignUpEvent()
    object Phone: SignUpEvent()
    object Email: SignUpEvent()
}