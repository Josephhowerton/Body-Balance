package com.fitness.onboard.onboard.goal.viewmodel

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.data.model.domain.user.UserBasicGoalsInfoDomain
import com.fitness.domain.usecase.cache.CreateUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.onboard.onboard.goal.GoalStateHolder
import com.fitness.onboard.util.OnboardFailure
import com.fitness.onboard.util.toOnboardFailure
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is GoalEvent.ForceComplete -> onForceComplete()
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.toOnboardFailure())
    }

    private fun onGoals (event: GoalEvent.Goals){
        stateHolder.updateState(stateHolder.getState().copy(goals = event.goals))
        setState(BaseViewState.Data(GoalState(GoalStep.SAVE_INFO)))
    }

    private fun onGetCurrentUserID() = safeLaunch {
        execute(currentUserIdUseCase(GetCurrentUserIdUseCase.Params)){
            onVerify(it)
        }
    }

    private fun onVerify(id: String){
        val holder = stateHolder.getState()
        val goals = holder.goals
        if(goals != null){
            val userGoalsInfo = UserBasicGoalsInfoDomain(userId = id, goals = goals)
            onSaveFitnessLevelsInfo(userGoalsInfo)
        }
        else{
            setState(BaseViewState.Error(OnboardFailure.UnknownFailure()))
        }
    }

    private fun onSaveFitnessLevelsInfo(userGoalsInfo: UserBasicGoalsInfoDomain) = safeLaunch {
        val param = CreateUserBasicGoalsInfoUseCase.Params(userGoalsInfo)
        execute(createUserBasicNutritionInfoUseCase(param)){
            authenticationManager.update(AuthenticationState.Authenticated)
        }
    }

    private fun onForceComplete() = authenticationManager.update(AuthenticationState.Authenticated)
}