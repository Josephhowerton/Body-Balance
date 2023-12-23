package com.fitness.authentication.reset.viewmodel

import auth.handleAuthFailure
import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.verifyPhone
import extensions.TextFieldState
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject
class ResetPhoneViewModel @Inject constructor(): IntentViewModel<BaseViewState<ResetPhoneNumberState>, ResetPhoneNumberEvent>() {

    init {
        setState(BaseViewState.Data(ResetPhoneNumberState()))
    }

    override fun onTriggerEvent(event: ResetPhoneNumberEvent) {
        when (event){
            is ResetPhoneNumberEvent.ResetPhoneNumber -> {
                verifyPhoneCredentials(event)
            }
        }
    }

    private fun verifyPhoneCredentials(event: ResetPhoneNumberEvent.ResetPhoneNumber){
        val (oldPhoneState, oldPhoneError) = verifyPhone(event.oldPhoneNumber)
        val (newPhoneState, newPhoneError) = verifyPhone(event.newPhoneNumber)
        if(isVerified(oldPhoneError, newPhoneError)){
            onPhoneReset()
        }
        else{
            onPhoneCredentialsError(
                oldPhoneState = oldPhoneState,
                newPhoneState = newPhoneState,
                oldPhoneError = oldPhoneError,
                newPhoneError = newPhoneError
            )
        }
    }

    private fun onPhoneCredentialsError(
        oldPhoneState: TextFieldState,
        newPhoneState: TextFieldState,
        oldPhoneError: Int,
        newPhoneError: Int
    ){
        setState(
            BaseViewState.Data(
                ResetPhoneNumberState(
                    oldPhoneState=oldPhoneState,
                    newPhoneState=newPhoneState,
                    oldPhoneError=oldPhoneError,
                    newPhoneError=newPhoneError
                )
            )
        )
    }

    private fun onPhoneReset() = safeLaunch {}

}