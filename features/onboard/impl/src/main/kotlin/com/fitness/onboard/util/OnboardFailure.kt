package com.fitness.onboard.util

import failure.Failure

sealed class OnboardFailure {
    data class UnknownFailure(override val description: Int = 1, override val title: Int = 2) : Failure()
    data class SystemFailure(override val description: Int = 1, override val title: Int = 2) : Failure()
    data class UserFailure(override val description: Int = 1, override val title: Int = 2) : Failure()
}