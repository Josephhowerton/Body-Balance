package com.fitness.authentication

data class SignInEmailCredentials(val email: String, val password: String)
data class SignUpEmailCredentials(val firstName:String, val lastName: String, val email:String, val password: String)