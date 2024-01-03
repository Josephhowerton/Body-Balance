package com.fitness.onboard.onboard.goal.viewmodel

import enums.EGoals

data class GoalState(val step: GoalStep = GoalStep.GOALS)


sealed class GoalEvent{
    data class Goals(val goals: List<EGoals>): GoalEvent()
    object SaveInfo: GoalEvent()
    object ForceComplete: GoalEvent()
}

enum class GoalStep {
    GOALS,
    SAVE_INFO
}