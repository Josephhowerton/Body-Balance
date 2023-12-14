package com.fitness.authentication.signup.viewmodel

data class SignUpState (val isVerified: Boolean = false)

sealed class SignUpEvent {
    data class EmailPasswordAuthData(val firstname: String, val lastname: String, val email:String, val password:String) : SignUpEvent()
    data class GoogleAuthData(val firstname: String, val lastname: String) : SignUpEvent()
    data class PhoneAuthData(val firstname: String, val lastname: String, val phoneNumber: String) : SignUpEvent()
    data class TwitterAuthData(val firstname: String, val lastname: String) : SignUpEvent()
    object Reset : SignUpEvent()
}