package com.fitness.authentication.signin.viewmodel

data class SignInState(
    var isVerified: Boolean = false,
    var isLoginComplete: Boolean = false
)

sealed class SignInEvent {
    data class EmailPasswordAuthData(val email:String, val password:String): SignInEvent()
    data class GoogleAuthData(val token: String): SignInEvent()
    data class PhoneAuthData(val phoneNumber: String): SignInEvent()
    data class TwitterAuthData(val token: String): SignInEvent()
    object Reset: SignInEvent()
}