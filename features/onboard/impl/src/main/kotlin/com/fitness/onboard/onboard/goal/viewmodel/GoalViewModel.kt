package com.fitness.onboard.onboard.goal.viewmodel

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.domain.model.user.UserBasicGoalsInfo
import com.fitness.domain.usecase.user.CreateUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.user.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.goal.GoalStateHolder
import com.fitness.onboard.util.GOALS_MIN_SELECTION
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.isMinSelection
import com.fitness.onboard.util.toOnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.MinimumNumberOfSelectionFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val stateHolder: GoalStateHolder,
    private val authenticationManager: AuthenticationManager,
    private val currentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createUserBasicNutritionInfoUseCase: CreateUserBasicGoalsInfoUseCase
): IntentViewModel<BaseViewState<GoalState>, GoalEvent>(){

    init { setState(BaseViewState.Data(GoalState())) }

    override fun onTriggerEvent(event: GoalEvent) {
        when(event){
            is GoalEvent.Goals -> onGoals (event)
            is GoalEvent.SaveInfo -> onGetCurrentUserID()
            is GoalEvent.DismissDialog -> onDismissDialog()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onDismissDialog(){
        val state = stateHolder.getState()
        setState(BaseViewState.Data( GoalState(step = state.currentStep, goals = state.goals)))
    }

    private fun onGoals (event: GoalEvent.Goals){
        stateHolder.updateState(stateHolder.getState().copy(goals = event.goals))
        if (isMinSelection(event.goals.size, GOALS_MIN_SELECTION)) {
            setState(BaseViewState.Data(GoalState(GoalStep.SAVE_INFO)))
        }
        else{
            setState(BaseViewState.Error(MinimumNumberOfSelectionFailure(minSelection = GOALS_MIN_SELECTION)))
        }
    }

    private fun onGetCurrentUserID() = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)){
            onVerify(it)
        }
    }

    private fun onVerify(id: String){
        val holder = stateHolder.getState()
        val goals = holder.goals
        if(goals.isNotEmpty()){
            val userGoalsInfo = UserBasicGoalsInfo(userId = id, goals = goals)
            onSaveFitnessLevelsInfo(userGoalsInfo)
        }
        else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveFitnessLevelsInfo(userGoalsInfo: UserBasicGoalsInfo) = safeLaunch {
        val param = CreateUserBasicGoalsInfoUseCase.Params(userGoalsInfo)
        execute(createUserBasicNutritionInfoUseCase(param)){
            setState(BaseViewState.Data(GoalState(GoalStep.COMPLETE)))
        }
    }
}