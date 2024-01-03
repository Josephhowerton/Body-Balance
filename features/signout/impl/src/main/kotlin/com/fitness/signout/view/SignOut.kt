package com.fitness.signout.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fitness.component.screens.LoadingScreen
import com.fitness.signout.viewmodel.SignOutEvent
import com.fitness.signout.viewmodel.SignOutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState


@Composable
fun SignOut(
    state: StateFlow<BaseViewState<SignOutState>> = MutableStateFlow(BaseViewState.Data(SignOutState())),
    onTriggerEvent: (SignOutEvent) -> Unit,
){
    val uiState by state.collectAsState()

    when(uiState){
        is BaseViewState.Loading,
        is BaseViewState.Data -> {
            LoadingScreen()
            onTriggerEvent(SignOutEvent.SignOut)
        }
        is BaseViewState.Error,
        is BaseViewState.Empty -> {
            LoadingScreen()
            onTriggerEvent(SignOutEvent.ForceSignOut)
        }
    }
}