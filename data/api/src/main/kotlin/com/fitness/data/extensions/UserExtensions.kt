package com.fitness.data.extensions

import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserFitnessLevelCache
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
fun UserBasicInfoDomain.toUserBasicInfoCache(): UserBasicInfoCache {
    return UserBasicInfoCache(
        userId = this.userId,
        age = this.age,
        gender = this.gender,
        height = this.height,
        weight = this.weight
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

fun UserFitnessLevelCache.toUserFitnessLevelDomain(): UserFitnessLevelDomain {
    return UserFitnessLevelDomain(
        userId = this.userId,
        level = this.level,
        habits = this.habits
    )
}


fun UserFitnessLevelDomain.toUserFitnessLevelCache(): UserFitnessLevelCache {
    return UserFitnessLevelCache(
        userId = this.userId,
        level = this.level,
        habits = this.habits
    )
}

fun UserBasicNutritionInfoDomain.toUserBasicNutritionInfoCache(): UserBasicNutritionInfoCache {
    return UserBasicNutritionInfoCache(
        userId = userId,
        restrictions = restrictions,
        preferences = preferences,
    )
}

fun UserBasicNutritionInfoCache.toUserBasicNutritionInfoDomain(): UserBasicNutritionInfoDomain {
    return UserBasicNutritionInfoDomain(
        userId = userId,
        restrictions = restrictions,
        preferences = preferences,
    )
}

fun UserBasicGoalsInfoDomain.toUserBasicGoalsInfoCache(): UserBasicGoalsInfoCache {
    return UserBasicGoalsInfoCache(
        userId = userId,
        goals = goals
    )
}

fun UserBasicGoalsInfoCache.toUserBasicGoalsInfoDomain(): UserBasicGoalsInfoDomain {
    return UserBasicGoalsInfoDomain(
        userId = userId,
        goals = goals
    )
}



