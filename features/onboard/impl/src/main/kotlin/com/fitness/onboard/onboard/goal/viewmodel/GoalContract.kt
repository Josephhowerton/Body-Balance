package com.fitness.onboard.onboard.goal.viewmodel

import enums.EGoals

data class GoalState(val step: GoalStep = GoalStep.GOALS, val goals: List<EGoals> = emptyList())


sealed class GoalEvent{
    data class Goals(val goals: List<EGoals>): GoalEvent()
    object SaveInfo: GoalEvent()
    object DismissDialog: GoalEvent()
}

enum class GoalStep {
    GOALS,
    SAVE_INFO,
    COMPLETE,
}