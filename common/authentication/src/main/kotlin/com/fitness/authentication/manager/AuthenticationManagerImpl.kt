package com.fitness.authentication.manager

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthenticationManagerImpl @Inject constructor(firebaseAuth: FirebaseAuth): AuthenticationManager{

    private var _authState: MutableStateFlow<AuthenticationState> = MutableStateFlow(
        if(firebaseAuth.currentUser != null){
            AuthenticationState.Authenticated
        }
        else{
            AuthenticationState.UnAuthenticated
        }
    )

    override val authState: StateFlow<AuthenticationState> get() = _authState

    override fun update(authState: AuthenticationState) {
        _authState.value = authState
    }
}