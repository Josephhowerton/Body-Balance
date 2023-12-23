package com.fitness.authentication.reset.viewmodel

import auth.handleAuthFailure
import com.fitness.authentication.util.isVerified
import com.fitness.authentication.util.verifyEmail
import com.fitness.domain.usecase.auth.SendPasswordResetEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import extensions.TextFieldState
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject


@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val sendPasswordResetEmailUseCase: SendPasswordResetEmailUseCase
): IntentViewModel<BaseViewState<ResetPasswordState>, ResetPasswordEvent>() {

    init {
        setState(BaseViewState.Data(ResetPasswordState()))
    }
    override fun onTriggerEvent(event: ResetPasswordEvent) {
        when (event){
            is ResetPasswordEvent.SendPasswordResetEmail -> {
                verifyEmailCredentials(event)
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception.handleAuthFailure())
    }

    private fun verifyEmailCredentials(event: ResetPasswordEvent.SendPasswordResetEmail){
        val (emailState, emailError) = verifyEmail(event.email)
        if(isVerified(emailError)){
            onSendPasswordResetEmail(event.email)
        }
        else{
            onEmailCredentialsError(emailState=emailState, emailError=emailError)
        }
    }

    private fun onEmailCredentialsError(emailState: TextFieldState, emailError: Int){
        setState(
            BaseViewState.Data(
                ResetPasswordState(
                    emailState=emailState,
                    emailError=emailError
                )
            )
        )
    }

    private fun onSendPasswordResetEmail(email: String) = safeLaunch {
        execute(sendPasswordResetEmailUseCase(SendPasswordResetEmailUseCase.Params(email=email))){
            setState(BaseViewState.Data(ResetPasswordState(isComplete = true)))
        }
    }
}