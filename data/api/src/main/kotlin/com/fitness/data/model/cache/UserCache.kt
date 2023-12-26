package com.fitness.data.model.cache

data class UserCache(
    val id: String,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?,
    val profilePictureUrl: String?,
    val isTermAndPrivacyAccepted: Boolean,
    val lastUpdated: Long
)

