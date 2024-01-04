package com.fitness.onboard.util

import com.fitness.resources.R
import failure.AuthStateFailure
import failure.Failure
import failure.TimeoutCancellationFailure
import kotlinx.coroutines.TimeoutCancellationException

sealed class OnboardFailure : Failure() {
    data class UnknownFailure(override val description: Int = 1, override val title: Int = 2) :
        OnboardFailure()

    data class SystemFailure(override val description: Int = 1, override val title: Int = 2) :
        OnboardFailure()

    data class UserFailure(override val description: Int = 1, override val title: Int = 2) :
        OnboardFailure()
}

fun Throwable.toOnboardFailure(): Failure =
    when(this){
        is AuthStateFailure -> AuthStateFailure()
        is TimeoutCancellationException -> TimeoutCancellationFailure()
        else -> OnboardFailure.UnknownFailure(description = R.string.desc_onboarding_unknown_failure, title = R.string.title_onboarding_unknown_failure)
    }