package com.fitness.onboard.util

import com.fitness.resources.R
import failure.AuthStateFailure
import failure.Failure
import failure.MinimumNumberOfSelectionFailure
import failure.TimeoutCancellationFailure
import kotlinx.coroutines.TimeoutCancellationException

sealed class OnboardFailure : Failure() {
    data class UnknownFailure(override val description: Int = R.string.desc_onboarding_unknown_failure, override val title: Int = R.string.title_onboarding_unknown_failure) : OnboardFailure()
    data class SystemFailure(override val description: Int = R.string.desc_onboarding_unknown_failure, override val title: Int = R.string.title_onboarding_unknown_failure) : OnboardFailure()
    data class UserFailure(override val description: Int = R.string.desc_onboarding_unknown_failure, override val title: Int = R.string.title_onboarding_unknown_failure) : OnboardFailure()
}

fun Throwable.toOnboardFailure(): Failure =
    when(this){
        is AuthStateFailure -> this
        is MinimumNumberOfSelectionFailure -> this
        is TimeoutCancellationException -> TimeoutCancellationFailure()
        else -> OnboardFailure.UnknownFailure(description = R.string.desc_onboarding_unknown_failure, title = R.string.title_onboarding_unknown_failure)
    }