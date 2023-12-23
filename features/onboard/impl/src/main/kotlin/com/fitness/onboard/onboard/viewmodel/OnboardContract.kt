package com.fitness.onboard.onboard.viewmodel


data class OnboardState(val id: String = "")

sealed class OnboardEvent {
    data class FitnessGoals(val goal: String): OnboardEvent()
}