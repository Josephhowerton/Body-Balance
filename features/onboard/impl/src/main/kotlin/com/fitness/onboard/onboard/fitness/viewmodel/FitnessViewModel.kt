package com.fitness.onboard.onboard.fitness.viewmodel

import com.fitness.onboard.onboard.fitness.FitnessStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class FitnessViewModel @Inject constructor(
    private val stateHolder: FitnessStateHolder
): IntentViewModel<BaseViewState<FitnessState>, FitnessEvent>(){

    override fun onTriggerEvent(event: FitnessEvent) {
        when(event){
            is FitnessEvent.FitnessLevels -> onFitnessLevels(event)

            is FitnessEvent.Habits -> onHabits(event)

            is FitnessEvent.Goals-> onGoals(event)

            is FitnessEvent.SaveFitnessInfo -> onVerify()
        }
    }


    private fun onFitnessLevels(event: FitnessEvent.FitnessLevels){
        stateHolder.updateState(stateHolder.getState().copy(currentFitnessLevel = event.currentFitnessLevel))
        setState(BaseViewState.Data(FitnessState(FitnessStep.HABITS)))
    }

    private fun onHabits(event: FitnessEvent.Habits){
        stateHolder.updateState(stateHolder.getState().copy(exerciseHabits = event.exerciseHabits))
        setState(BaseViewState.Data(FitnessState(FitnessStep.GOALS)))
    }

    private fun onGoals(event: FitnessEvent.Goals){
        stateHolder.updateState(stateHolder.getState().copy(fitnessGoals = event.fitnessGoals))
        setState(BaseViewState.Data(FitnessState(FitnessStep.SAVE_FITNESS_INFO)))
    }

    private fun onVerify(){

    }

    private fun onSaveFitnessLevelsInfo(){

    }
}