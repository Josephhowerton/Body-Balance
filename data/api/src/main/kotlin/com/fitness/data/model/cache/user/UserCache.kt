package com.fitness.data.model.cache.user

data class UserCache(
    val id: String = "",
    val displayName: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val profilePictureUrl: String? = "",
    val isTermAndPrivacyAccepted: Boolean = false,
    val lastUpdated: Long = 0L,
    val isNewUser: Boolean = false,
    val userPreferences:UserPreferencesCache? = null
)

