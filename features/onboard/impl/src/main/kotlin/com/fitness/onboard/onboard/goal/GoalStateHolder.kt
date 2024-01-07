package com.fitness.onboard.onboard.goal

import com.fitness.onboard.onboard.goal.viewmodel.GoalStep
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

data class GoalState(val currentStep: GoalStep = GoalStep.GOALS, val goals: List<EGoals> = emptyList())
