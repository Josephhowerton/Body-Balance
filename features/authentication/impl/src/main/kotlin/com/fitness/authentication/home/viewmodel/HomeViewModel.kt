package com.fitness.authentication.home.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): IntentViewModel<BaseViewState<HomeState>, HomeEvent>() {

    init {
        setState(
            BaseViewState.Data(HomeState())
        )
    }
    override fun onTriggerEvent(event: HomeEvent) {
        when(event){

            is HomeEvent.CheckBoxChanged -> {
                onCheckBoxChanged(event)
            }

            is HomeEvent.SignIn -> {
                onSignIn(event)
            }

            is HomeEvent.SignUp -> {
                onSignUp(event)
            }

        }
    }

    private fun onCheckBoxChanged(event: HomeEvent.CheckBoxChanged){
        val state = if(event.newState){
            CheckBoxState.OK
        }
        else{
            CheckBoxState.NONE
        }

        setState(BaseViewState.Data(HomeState(hasAgreed = event.newState, checkBoxState = state)))
    }
    private fun onSignIn(event: HomeEvent.SignIn){
        val state = if(event.hasAgreed){
            startLoading()
            HomeState(hasAgreed = true, direction = Direction.SIGN_IN)
        }
        else{
            HomeState(checkBoxState = CheckBoxState.ERROR)
        }

        setState(BaseViewState.Data(state))
    }

    private fun onSignUp(event: HomeEvent.SignUp){
        val state = if(event.hasAgreed){
            startLoading()
            HomeState(hasAgreed = true, direction = Direction.SIGN_UP)
        }
        else{
            HomeState(checkBoxState = CheckBoxState.ERROR)
        }

        setState(BaseViewState.Data(state))
    }
}