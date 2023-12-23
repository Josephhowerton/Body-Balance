package com.fitness.authentication.reset.viewmodel

import extensions.TextFieldState

data class ResetPasswordState (
    val emailState: TextFieldState = TextFieldState.PENDING,
    val emailError: Int? = null,
    var isComplete: Boolean = false
)

data class ResetEmailState (
    val oldEmailState: TextFieldState = TextFieldState.PENDING,
    val newEmailState: TextFieldState = TextFieldState.PENDING,
    val oldEmailError: Int? = null,
    val newEmailError: Int? = null
)

data class ResetPhoneNumberState (
    val oldPhoneState: TextFieldState = TextFieldState.PENDING,
    val newPhoneState: TextFieldState = TextFieldState.PENDING,
    val oldPhoneError: Int? = null,
    val newPhoneError: Int? = null
)

sealed class ResetPasswordEvent {
    data class SendPasswordResetEmail(val email: String) : ResetPasswordEvent()
}

sealed class ResetEmailEvent {
    data class ResetEmail(val oldEmail: String, val newEmail: String): ResetEmailEvent()
}

sealed class ResetPhoneNumberEvent {
    data class ResetPhoneNumber(val oldPhoneNumber: String, val newPhoneNumber: String): ResetPhoneNumberEvent()
}
