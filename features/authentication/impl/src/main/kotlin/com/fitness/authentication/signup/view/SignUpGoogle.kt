package com.fitness.authentication.signup.view

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.util.AuthMethod
import com.fitness.resources.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GoogleBottomAuthSheet(
    onSignInResult: (GoogleSignInAccount?) -> Unit,
    onTriggerEvent: (SignUpEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("909661259181-gf0ah1pg3s6u8fnd58825flknrpm03ha.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            coroutineScope.launch {
                try {
                    val authResult = Firebase.auth.signInWithCredential(credential).await()
                    onSignInResult(account)
                    // Handle Firebase auth success
                } catch (firebaseEx: FirebaseAuthException) {
                    // Handle Firebase auth error
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Failed to sign in with Google", e)
            // Handle Google Sign-In error
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.NONE)) },
        sheetState = sheetState,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.auth_button_title_google),
                    style = MaterialTheme.typography.headlineMedium
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.NONE))
                            launcher.launch(googleSignInClient.signInIntent)
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.title_continue))
                }
            }
        }
    )
}
