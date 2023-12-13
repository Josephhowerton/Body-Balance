package com.fitness.authentication.signin.viewmodel

data class LoginState(
    var isVerified: Boolean = false,
    var isLoginComplete: Boolean = false
)

sealed class LoginEvent {
    data class EmailPasswordAuthData(val email:String, val password:String): LoginEvent()
    data class GoogleAuthData(val token: String): LoginEvent()
    data class PhoneAuthData(val phoneNumber: String): LoginEvent()
    data class TwitterAuthData(val token: String): LoginEvent()
    object Reset: LoginEvent()
}