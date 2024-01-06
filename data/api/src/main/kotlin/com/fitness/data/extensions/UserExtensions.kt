package com.fitness.data.extensions

import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.model.domain.user.UserBasicGoalsInfoDomain
import com.fitness.data.model.domain.user.UserBasicInfoDomain
import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.data.model.domain.user.UserDomain
import com.fitness.data.model.domain.user.UserFitnessLevelDomain
import com.google.firebase.auth.FirebaseUser

// Extension function to convert FirebaseUser to UserDomain
fun FirebaseUser.toUserDomain(): UserDomain {
    return UserDomain(
        id = this.uid,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = false,
        profilePictureUrl = this.photoUrl?.toString(),
        isNewUser = false
    )
}

// Extension function to convert UserCache to UserDomain
fun UserCache.toUserDomain(): UserDomain {
    return UserDomain(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = this.isTermAndPrivacyAccepted,
        profilePictureUrl = this.profilePictureUrl,
        isNewUser = isNewUser
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
        isTermAndPrivacyAccepted = this.isTermAndPrivacyAccepted,
        lastUpdated = lastUpdated,
        isNewUser = isNewUser ?: false
    )
}


// Extension function to convert UserBasicInfoDomain to UserBasicInfoCache
fun UserBasicInfoDomain.toUserBasicInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicInfoCache {
    return UserBasicInfoCache(
        userId = this.userId,
        age = this.age,
        gender = this.gender,
        height = this.height,
        weight = this.weight,
        lastUpdated = lastUpdated
    )
}

// Extension function to convert UserBasicInfoCache to UserBasicInfoDomain
fun UserBasicInfoCache.toUserBasicInfoDomain(): UserBasicInfoDomain {
    return UserBasicInfoDomain(
        userId = this.userId,
        age = this.age,
        gender = this.gender,
        height = this.height,
        weight = this.weight
    )
}

fun UserBasicFitnessLevelCache.toUserFitnessLevelDomain(): UserFitnessLevelDomain {
    return UserFitnessLevelDomain(
        userId = this.userId,
        level = this.level,
        habits = this.habits
    )
}


fun UserFitnessLevelDomain.toUserFitnessLevelCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicFitnessLevelCache {
    return UserBasicFitnessLevelCache(
        userId = this.userId,
        level = this.level,
        habits = this.habits,
        lastUpdated = lastUpdated
    )
}

fun UserBasicNutritionInfoDomain.toUserBasicNutritionInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicNutritionInfoCache {
    return UserBasicNutritionInfoCache(
        userId = userId,
        restrictions = restrictions,
        labels = labels,
        cuisineType = cuisineType,
        lastUpdated = lastUpdated
    )
}

fun UserBasicNutritionInfoCache.toUserBasicNutritionInfoDomain(): UserBasicNutritionInfoDomain {
    return UserBasicNutritionInfoDomain(
        userId = userId,
        restrictions = restrictions,
        labels = labels,
        cuisineType = cuisineType
    )
}

fun UserBasicGoalsInfoDomain.toUserBasicGoalsInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicGoalsInfoCache {
    return UserBasicGoalsInfoCache(
        userId = userId,
        goals = goals,
        lastUpdated = lastUpdated
    )
}

fun UserBasicGoalsInfoCache.toUserBasicGoalsInfoDomain(): UserBasicGoalsInfoDomain {
    return UserBasicGoalsInfoDomain(
        userId = userId,
        goals = goals
    )
}



