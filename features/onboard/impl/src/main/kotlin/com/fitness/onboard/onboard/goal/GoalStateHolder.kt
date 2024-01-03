package com.fitness.onboard.onboard.goal

import enums.EGoals


object GoalStateHolder {
    private var goalState: GoalState = GoalState()

    fun getState() = goalState
    fun updateState(newState: GoalState) {
        goalState = newState
    }

    fun clearState() {
        goalState = GoalState()
    }
}

data class GoalState(val goals: List<EGoals>? = null)
