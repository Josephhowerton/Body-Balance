package com.fitness.data.model.model.user

data class UserDomain(
    val id: String,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?,
    val profilePictureUrl: String?
)
