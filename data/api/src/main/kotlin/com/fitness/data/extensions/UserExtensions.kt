package com.fitness.data.extensions

import com.fitness.data.model.cache.UserCache
import com.fitness.data.model.model.user.UserDomain
import com.google.firebase.auth.FirebaseUser

// Extension function to convert FirebaseUser to UserDomain
fun FirebaseUser.toUserDomain(): UserDomain {
    return UserDomain(
        id = this.uid,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        profilePictureUrl = this.photoUrl?.toString()
    )
}

// Extension function to convert UserCache to UserDomain
fun UserCache.toUserDomain(): UserDomain {
    return UserDomain(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        profilePictureUrl = this.profilePictureUrl
    )
}

// Extension function to convert UserDomain to UserCache
fun UserDomain.toUserCache(lastUpdated: Long = System.currentTimeMillis()): UserCache {
    return UserCache(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        profilePictureUrl = this.profilePictureUrl,
        lastUpdated = lastUpdated
    )
}


