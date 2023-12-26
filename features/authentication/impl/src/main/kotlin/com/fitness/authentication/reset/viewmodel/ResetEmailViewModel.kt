package com.fitness.authentication.reset.viewmodel

import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.verifyEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.TextFieldState
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class ResetEmailViewModel @Inject constructor(): IntentViewModel<BaseViewState<ResetEmailState>, ResetEmailEvent>() {

    init {
        setState(BaseViewState.Data(ResetEmailState()))
    }
    override fun onTriggerEvent(event: ResetEmailEvent) {
        when (event){
            is ResetEmailEvent.ResetEmail -> {
                verifyEmailCredentials(event)
            }
        }
    }

    private fun verifyEmailCredentials(event: ResetEmailEvent.ResetEmail){
        val (oldEmailState, oldEmailError) = verifyEmail(event.oldEmail)
        val (newEmailState, newEmailError) = verifyEmail(event.newEmail)
        if(isVerified(oldEmailError, newEmailError)){
            onEmailReset()
        }
        else{
            onEmailCredentialsError(
                oldEmailState = oldEmailState,
                newEmailState = newEmailState,
                oldEmailError = oldEmailError,
                newEmailError = newEmailError
            )
        }
    }

    private fun onEmailCredentialsError(
        oldEmailState: TextFieldState,
        newEmailState: TextFieldState,
        oldEmailError: Int,
        newEmailError: Int
    ){
        setState(
            BaseViewState.Data(
                ResetEmailState(
                    oldEmailState=oldEmailState,
                    newEmailState=newEmailState,
                    oldEmailError=oldEmailError,
                    newEmailError=newEmailError
                )
            )
        )
    }

    private fun onEmailReset() = safeLaunch {}

}