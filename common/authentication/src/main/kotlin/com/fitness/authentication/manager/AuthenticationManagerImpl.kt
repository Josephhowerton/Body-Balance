package com.fitness.authentication.manager

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthenticationManagerImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthenticationManager, AuthStateListener  {
    init { firebaseAuth.addAuthStateListener(this) }
    override val authState: StateFlow<AuthState> get() =
        MutableStateFlow(
            if(firebaseAuth.currentUser != null){
                AuthState.Authenticated
            }
            else{
                AuthState.UnAuthenticated
            }
        )

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        if(authState.value == AuthState.Authenticated){
            Log.e("AuthenticationManagerImpl", "Authenticated")
        }else{
            Log.e("AuthenticationManagerImpl", "UnAuthenticated")
        }
    }

}