package com.fitness.authentication.signin.view

import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import auth.AuthFailure
import com.fitness.authentication.navigation.AuthEntryImpl
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.resetPassword
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signInPhone
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUp
import com.fitness.authentication.signin.viewmodel.SignInEvent
import com.fitness.authentication.signin.viewmodel.SignInState
import com.fitness.authentication.util.AuthMethod
import com.fitness.authentication.util.DisplayErrorMessage
import com.fitness.authentication.util.DisplayFieldState
import com.fitness.authentication.util.ForgotPasswordAnnotatedText
import com.fitness.authentication.util.NewNumberAnnotatedText
import com.fitness.authentication.util.PasswordTrailingIcon
import com.fitness.authentication.util.SignUpForFreeAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardIconButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import extensions.TextFieldState
import extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import state.BaseViewState


@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInEmailPreview() = BodyBalanceTheme {
    Surface {
        SignInEmailContent(state = SignInState(), {}, {})
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInPhonePreview() = BodyBalanceTheme {
    Surface {
        SignInPhoneContent(state = SignInState(), {}, {})
    }
}

@Composable
fun SignInEmailScreen(
    state: StateFlow<BaseViewState<SignInState>> = MutableStateFlow(BaseViewState.Data(SignInState())),
    onPopBack: () -> Unit = {},
    onTriggerEvent: (SignInEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit,
    onComplete: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<SignInState>>().value
            if(currentState.isAuthenticated){
                onComplete()
            }
            else{
                SignInEmailContent(
                    state = currentState,
                    onTriggerEvent = onTriggerEvent,
                    onTriggerNavigation = onTriggerNavigation,
                )
            }
        }
        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onPopBack()
            }
        }
        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(message = R.string.unknown, onClick = onPopBack)
        }
    }

}

@Composable
fun SignInEmailContent(
    state: SignInState,
    onTriggerEvent: (SignInEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            welcome,
            message,
            email,
            password,
            resetPassword,
            annotated,
            or,
            phone,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        val passwordRequester = remember { FocusRequester() }

        handleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

        StandardText(
            text = R.string.not_a_member,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(message.top)
            }
        )

        SignUpForFreeAnnotatedText(
            onClick = { onTriggerNavigation(signUp) },
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            value = userEmail,
            hint = R.string.enter_email,
            label = R.string.label_email,
            isError = state.emailState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.emailState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.emailState,
                    errorMessageId = state.emailErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            onValueChanged = { userEmail = it },
            modifier = Modifier
                .focusRequester(passwordRequester)
                .constrainAs(email) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(message.bottom, 15.dp)
                    width = Dimension.fillToConstraints
                }
        )

        StandardTextField(
            value = userPassword,
            hint = R.string.enter_password,
            label = R.string.label_password,
            isError = state.passwordState == TextFieldState.ERROR,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                PasswordTrailingIcon(
                    state = state.passwordState,
                    isVisible = isPasswordVisible,
                    onIconClick = { isPasswordVisible = !isPasswordVisible }
                )
            },
            supportingText = {
                DisplayErrorMessage(
                    state = state.passwordState,
                    errorMessageId = state.passwordErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onTriggerEvent(
                    SignInEvent.EmailPasswordAuthentication(
                        email = userEmail,
                        password = userPassword
                    )
                )
            }),
            onValueChanged = { userPassword = it },
            modifier = Modifier.constrainAs(password) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(email.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        ForgotPasswordAnnotatedText(
            onClick = { onTriggerNavigation(AuthEntryImpl.resetPassword) },
            modifier = Modifier.constrainAs(resetPassword) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(password.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = { onTriggerEvent(SignInEvent.EmailPasswordAuthentication(email=userEmail, password=userPassword)) },
            modifier = Modifier.constrainAs(annotated) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(resetPassword.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.auth_button_phone,
            onClick = { onTriggerNavigation(signInPhone) },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(annotated.bottom, 5.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextSmall(
            text = R.string.or,
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(or) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(phone.bottom)
                }
        )

        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(google) {
                    start.linkTo(startGuideline)
                    end.linkTo(facebook.start)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_facebook_logo,
            desc = R.string.content_description_facebook,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(facebook) {
                    start.linkTo(google.end)
                    end.linkTo(x.start)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_x_logo,
            desc = R.string.content_description_x,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.X)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom, 10.dp)
                }
        )
    }
}

@Composable
fun SignInPhoneScreen(
    state: StateFlow<BaseViewState<SignInState>> = MutableStateFlow(BaseViewState.Data(SignInState())),
    onPopBack: () -> Unit = {},
    onTriggerEvent: (SignInEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit,
    onComplete: () -> Unit = {}
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<SignInState>>().value
            if(currentState.isAuthenticated){
                onComplete()
            }
            else{
                SignInPhoneContent(
                    state = currentState,
                    onTriggerEvent = onTriggerEvent,
                    onTriggerNavigation = onTriggerNavigation,
                )
            }
        }
        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onPopBack()
            }
        }
        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(message = R.string.unknown, onClick = onPopBack)
        }
    }
}

@Composable
fun SignInPhoneContent(
    state: SignInState,
    onTriggerEvent: (SignInEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            welcome,
            message,
            phone,
            forgot,
            annotated,
            or,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userPhone by remember { mutableStateOf("") }
        
        handleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

        StandardText(
            text = R.string.not_a_member,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(message.top)
            }
        )

        SignUpForFreeAnnotatedText(
            onClick = { onTriggerNavigation(signUp) },
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            value = userPhone,
            hint = R.string.enter_phone,
            label = R.string.label_phone,
            isError = state.phoneState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.phoneState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.phoneState,
                    errorMessageId = state.phoneErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onTriggerEvent(
                    SignInEvent.PhoneAuthentication(
                        phoneNumber = userPhone
                    )
                )
            }),
            onValueChanged = { userPhone = it },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        NewNumberAnnotatedText(
            onClick = { onTriggerNavigation(resetPassword) },
            modifier = Modifier.constrainAs(forgot) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = {
                onTriggerEvent(
                    SignInEvent.PhoneAuthentication(
                        phoneNumber = userPhone
                    )
                )
            },
            modifier = Modifier.constrainAs(annotated) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(forgot.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextSmall(
            text = R.string.or,
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(or) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(annotated.bottom)
                }
        )

        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(google) {
                    start.linkTo(startGuideline)
                    end.linkTo(facebook.start)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_facebook_logo,
            desc = R.string.content_description_facebook,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(facebook) {
                    start.linkTo(google.end)
                    end.linkTo(x.start)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_x_logo,
            desc = R.string.content_description_x,
            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.X)) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom, 10.dp)
                }
        )
    }
}

@Composable
private fun handleAuthMethod(authMethod: AuthMethod, onTriggerEvent: (SignInEvent) -> Unit) =
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


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun GoogleBottomAuthSheet(
    onSignInResult: (GoogleSignInAccount?) -> Unit,
    onTriggerEvent: (SignInEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("442236870595-opv98sfj7qc6fchuqgd8adrd012qtmes.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        val account = try {
            task.getResult(ApiException::class.java)
        } catch (e: ApiException) {
            null
        }
        onSignInResult(account)
    }

    ModalBottomSheet(
        onDismissRequest = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.NONE)) },
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
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.title_continue))
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FacebookBottomAuthSheet(onTriggerEvent: (SignInEvent) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.NONE)) },
        sheetState = sheetState,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.auth_button_title_facebook))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.title_continue))
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun XBottomAuthSheet(
    onTriggerEvent: (SignInEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.NONE)) },
        sheetState = sheetState,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.auth_button_title_x))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.title_continue))
                }
            }
        }
    )
}