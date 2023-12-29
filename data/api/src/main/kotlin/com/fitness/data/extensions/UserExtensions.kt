package com.fitness.data.extensions

import com.fitness.data.model.cache.UserCache
import com.fitness.data.model.model.user.UserAccountDomain
import com.google.firebase.auth.FirebaseUser

// Extension function to convert FirebaseUser to UserDomain
fun FirebaseUser.toUserDomain(): UserAccountDomain {
    return UserAccountDomain(
        id = this.uid,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = false,
        profilePictureUrl = this.photoUrl?.toString()
    )
}

// Extension function to convert UserCache to UserDomain
fun UserCache.toUserDomain(): UserAccountDomain {
    return UserAccountDomain(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = this.isTermAndPrivacyAccepted,
        profilePictureUrl = this.profilePictureUrl
    )
}

// Extension function to convert UserDomain to UserCache
fun UserAccountDomain.toUserCache(lastUpdated: Long = System.currentTimeMillis()): UserCache {
    return UserCache(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        profilePictureUrl = this.profilePictureUrl,
        isTermAndPrivacyAccepted = this.isTermAndPrivacyAccepted,
        lastUpdated = lastUpdated
    )
}


