package com.fitness.domain.model

import com.fitness.data.extensions.toApiParamFromECuisineType
import com.fitness.data.extensions.toApiParamFromEHealthLabel
import com.fitness.data.extensions.toCuisineType
import com.fitness.data.extensions.toDietaryRestrictions
import com.fitness.data.extensions.toEnumName
import com.fitness.data.extensions.toHealthLabel
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.google.firebase.auth.FirebaseUser
import com.fitness.domain.model.user.UserBasicGoalsInfo
import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.model.user.UserBasicNutritionInfo
import com.fitness.domain.model.user.User
import com.fitness.domain.model.user.UserFitnessLevel


// Extension function to convert FirebaseUser to UserDomain
fun FirebaseUser.toUserDomain(): User {
    return User(
        id = this.uid,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = false,
        profilePictureUrl = this.photoUrl?.toString(),
        isNewUser = false
    )
}

// Extension function to convert UserCache to User
fun UserCache.toUser(): User {
    return User(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        isTermAndPrivacyAccepted = this.isTermAndPrivacyAccepted,
        profilePictureUrl = this.profilePictureUrl,
        isNewUser = isNewUser
    )
}

// Extension function to convert User to UserCache
fun User.toUserCache(lastUpdated: Long = System.currentTimeMillis()): UserCache {
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


// Extension function to convert UserBasicInfo to UserBasicInfoCache
fun UserBasicInfo.toUserBasicInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicInfoCache {
    return UserBasicInfoCache(
        userId = this.userId,
        age = this.age,
        gender = this.gender,
        height = this.height,
        weight = this.weight,
        lastUpdated = lastUpdated
    )
}

// Extension function to convert UserBasicInfoCache to UserBasicInfo
fun UserBasicInfoCache.toUserBasicInfo(): UserBasicInfo {
    return UserBasicInfo(
        userId = this.userId,
        age = this.age,
        gender = this.gender,
        height = this.height,
        weight = this.weight
    )
}

fun UserBasicFitnessLevelCache.toUserFitnessLevel(): UserFitnessLevel {
    return UserFitnessLevel(
        userId = this.userId,
        level = this.level,
        habits = this.habits
    )
}


fun UserFitnessLevel.toUserFitnessLevelCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicFitnessLevelCache {
    return UserBasicFitnessLevelCache(
        userId = this.userId,
        level = this.level,
        habits = this.habits,
        lastUpdated = lastUpdated
    )
}

fun UserBasicNutritionInfo.toUserBasicNutritionInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicNutritionInfoCache {
    return UserBasicNutritionInfoCache(
        userId = userId,
        restrictions = restrictions.toEnumName(),
        healthLabels = healthLabels.toEnumName(),
        healthLabelsApi = healthLabels.toApiParamFromEHealthLabel(),
        cuisineType = cuisineType.toEnumName(),
        cuisineTypeApi = cuisineType.toApiParamFromECuisineType(),
        lastUpdated = lastUpdated
    )
}

fun UserBasicNutritionInfoCache.toUserBasicNutritionInfo(): UserBasicNutritionInfo {
    return UserBasicNutritionInfo(
        userId = userId,
        restrictions = restrictions.toDietaryRestrictions(),
        healthLabels = healthLabels.toHealthLabel(),
        cuisineType = cuisineType.toCuisineType(),
        healthLabelsApi = healthLabelsApi,
        cuisineTypeApi = cuisineTypeApi,
    )
}

fun UserBasicGoalsInfo.toUserBasicGoalsInfoCache(lastUpdated: Long = System.currentTimeMillis()): UserBasicGoalsInfoCache {
    return UserBasicGoalsInfoCache(
        userId = userId,
        goals = goals,
        lastUpdated = lastUpdated
    )
}

fun UserBasicGoalsInfoCache.toUserBasicGoalsInfo(): UserBasicGoalsInfo {
    return UserBasicGoalsInfo(
        userId = userId,
        goals = goals
    )
}
