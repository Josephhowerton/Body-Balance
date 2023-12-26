package com.fitness.data.model.model.user

data class UserDomain(
    val id: String,
    var displayName: String?,
    var email: String?,
    var phoneNumber: String?,
    var isTermAndPrivacyAccepted: Boolean,
    var profilePictureUrl: String?
)
