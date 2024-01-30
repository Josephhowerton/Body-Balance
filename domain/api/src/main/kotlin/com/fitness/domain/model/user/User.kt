package com.fitness.domain.model.user

data class User(
    val id: String,
    var displayName: String?,
    var email: String?,
    var phoneNumber: String?,
    var isTermAndPrivacyAccepted: Boolean,
    var profilePictureUrl: String?,
    var isNewUser: Boolean?,
    val userPreferences: UserPreferences
)
