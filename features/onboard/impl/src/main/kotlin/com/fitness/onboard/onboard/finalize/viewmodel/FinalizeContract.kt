package com.fitness.onboard.onboard.finalize.viewmodel


data class FinalizeState(val finalizeStep: FinalizeStep = FinalizeStep.INITIALIZE)
sealed class FinalizeEvent{
    object Initialize: FinalizeEvent()
    object SaveRecommendedMetrics: FinalizeEvent()
    object Complete: FinalizeEvent()
}

enum class FinalizeStep {
    INITIALIZE,
    SAVE_RECOMMENDED,
    COMPLETE
}