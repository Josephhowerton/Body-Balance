package com.fitness.authentication.signup.viewmodel

data class CreateAccountState (val isVerified: Boolean = false)

sealed class CreateAccountEvent {
    data class EmailPasswordAuthData(val firstname: String, val lastname: String, val email:String, val password:String) : CreateAccountEvent()
    data class GoogleAuthData(val firstname: String, val lastname: String) : CreateAccountEvent()
    data class PhoneAuthData(val firstname: String, val lastname: String, val phoneNumber: String) : CreateAccountEvent()
    data class TwitterAuthData(val firstname: String, val lastname: String) : CreateAccountEvent()
    object Reset : CreateAccountEvent()
}