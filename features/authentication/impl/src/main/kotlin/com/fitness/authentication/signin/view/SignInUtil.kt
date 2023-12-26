package com.fitness.authentication.signin.view

import android.util.Log
import androidx.compose.runtime.Composable
import com.fitness.authentication.signin.viewmodel.SignInEvent
import com.fitness.authentication.util.AuthMethod

@Composable
fun HandleSignInMethod(authMethod: AuthMethod, onTriggerEvent: (SignInEvent) -> Unit) =
    when (authMethod) {
        AuthMethod.GOOGLE -> {
            GoogleBottomAuthSheet(
                onSignInResult = {
                    if (it != null) {
                        Log.e("GoogleSignIn", "Success")
                    } else {
                        Log.e("GoogleSignIn", "Failed")
                    }
                },
                onTriggerEvent = onTriggerEvent
            )
        }

        AuthMethod.FACEBOOK -> {
            FacebookBottomAuthSheet(
                onTriggerEvent = onTriggerEvent
            )
        }

        AuthMethod.X -> {
            XBottomAuthSheet(
                onTriggerEvent = onTriggerEvent
            )
        }

        else -> {}
    }