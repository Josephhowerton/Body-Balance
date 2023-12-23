package com.fitness.authentication.signup.view

import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
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
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signInEmail
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUpEmail
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUpPhone
import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.signup.viewmodel.SignUpState
import com.fitness.authentication.util.AuthMethod
import com.fitness.authentication.util.DisplayErrorMessage
import com.fitness.authentication.util.DisplayFieldState
import com.fitness.authentication.util.PasswordTrailingIcon
import com.fitness.authentication.util.SignInAnnotatedText
import com.fitness.authentication.util.TermsAndPrivacyAnnotatedText
import com.fitness.component.components.BodyBalanceImageLogo
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardHeadlineText
import com.fitness.component.components.StandardIconButton
import com.fitness.component.components.StandardOutlinedIconButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties.END
import com.fitness.component.properties.GuidelineProperties.LOGO_BOTTOM
import com.fitness.component.properties.GuidelineProperties.LOGO_TOP
import com.fitness.component.properties.GuidelineProperties.SECOND_TOP
import com.fitness.component.properties.GuidelineProperties.START
import com.fitness.component.properties.GuidelineProperties.TOP
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import extensions.TextFieldState
import extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import state.BaseViewState


@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpPreview() {
    BodyBalanceTheme {
        Surface {
            SignUpContent(state = SignUpState(), onTriggerEvent = {}, onTriggerNavigation = {})
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpEmailPreview() = BodyBalanceTheme {
    Surface {
        SignUpEmailContent(state = SignUpState())
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpPhonePreview() {
    BodyBalanceTheme {
        Surface {
            SignUpPhoneContent(state = SignUpState())
        }
    }
}

@Composable
fun SignUpScreen(
    state: StateFlow<BaseViewState<SignUpState>> = MutableStateFlow(BaseViewState.Data(SignUpState())),
    onPopBack: () -> Unit,
    onTriggerEvent: (SignUpEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            SignUpContent(
                state = uiState.cast<BaseViewState.Data<SignUpState>>().value,
                onTriggerEvent = onTriggerEvent,
                onTriggerNavigation = onTriggerNavigation
            )
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
fun SignUpContent(
    state: SignUpState,
    onTriggerEvent: (SignUpEvent) -> Unit,
    onTriggerNavigation: (String) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            logo,
            welcome,
            message,
            email,
            phone,
            google,
            facebook,
            twitter,
            login,
        ) = createRefs()

        val logoTopGuideline = createGuidelineFromTop(LOGO_TOP)
        val logoBottomGuideline = createGuidelineFromTop(LOGO_BOTTOM)
        val startGuideline = createGuidelineFromStart(START)
        val endGuideline = createGuidelineFromEnd(END)

        handleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

        BodyBalanceImageLogo(
            modifier = Modifier
                .size(150.dp)
                .constrainAs(logo) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(logoTopGuideline)
                    bottom.linkTo(logoBottomGuideline)
                }
        )

        StandardTitleText(
            text = R.string.welcome_to_body_balance,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(logoBottomGuideline, 25.dp)
                bottom.linkTo(message.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardHeadlineText(
            text = R.string.create_account_message,
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 15.dp)
                bottom.linkTo(email.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_email,
            desc = R.string.content_description_email,
            text = R.string.auth_button_title_email,
            onClick = { onTriggerNavigation(signUpEmail) },
            modifier = Modifier.constrainAs(email) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                bottom.linkTo(phone.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_phone,
            desc = R.string.content_description_phone,
            text = R.string.auth_button_phone,
            onClick = { onTriggerNavigation(signUpPhone) },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(email.bottom, 10.dp)
                bottom.linkTo(google.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            text = R.string.auth_button_title_google,
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
            modifier = Modifier.constrainAs(google) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 10.dp)
                bottom.linkTo(facebook.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_facebook_logo,
            desc = R.string.content_description_facebook,
            text = R.string.auth_button_title_facebook,
            iconSize = 22,
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
            modifier = Modifier.constrainAs(facebook) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(google.bottom, 10.dp)
                bottom.linkTo(twitter.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_x_logo,
            desc = R.string.content_description_x,
            text = R.string.auth_button_title_x,
            iconSize = 16,
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.X)) },
            modifier = Modifier.constrainAs(twitter) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(facebook.bottom, 10.dp)
                bottom.linkTo(login.top)
                width = Dimension.fillToConstraints
            }
        )

        SignInAnnotatedText(
            onClick = { onTriggerNavigation(signInEmail) },
            modifier = Modifier.constrainAs(login) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(twitter.bottom, 25.dp)
            })
    }
}


@Composable
fun SignUpEmailScreen(
    state: StateFlow<BaseViewState<SignUpState>>,
    onPopBack: () -> Unit,
    onTriggerEvent: (SignUpEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit,
    onComplete: () -> Unit = {}

) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<SignUpState>>().value
            if (currentState.isSignUpCompleted) {
                onComplete()
            } else {
                SignUpEmailContent(
                    state = currentState,
                    onTriggerEvent = onTriggerEvent,
                    onTriggerNavigation = onTriggerNavigation
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
fun SignUpEmailContent(
    state: SignUpState,
    onTriggerEvent: (SignUpEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            signIn,
            welcome,
            message,
            firstname,
            lastname,
            email,
            password,
            signUp,
            termsService,
            or,
            phone,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(TOP)
        val logoBottomGuideline = createGuidelineFromTop(SECOND_TOP)
        val startGuideline = createGuidelineFromStart(START)
        val endGuideline = createGuidelineFromEnd(END)

        var userFirstname by remember { mutableStateOf("") }
        var userLastname by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }

        var isPasswordVisible by remember { mutableStateOf(false) }

        val lastnameRequester = remember { FocusRequester() }
        val emailRequester = remember { FocusRequester() }
        val passwordRequester = remember { FocusRequester() }

        handleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

        SignInAnnotatedText(
            onClick = { onTriggerNavigation(signInEmail) },
            modifier = Modifier.constrainAs(signIn) {
                end.linkTo(endGuideline)
                top.linkTo(topGuideline)
            })

        StandardText(
            text = R.string.welcome_to_body_balance,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(logoBottomGuideline)
                bottom.linkTo(message.top)
            }
        )

        StandardTextSmall(
            text = R.string.create_account_message,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            value = userFirstname,
            hint = R.string.enter_first_name,
            label = R.string.label_firstname,
            isError = state.firstnameState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.firstnameState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.firstnameState,
                    errorMessageId = state.firstnameErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChanged = { userFirstname = it },
            modifier = Modifier
                .focusRequester(lastnameRequester)
                .constrainAs(firstname) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(message.bottom, 15.dp)
                    width = Dimension.fillToConstraints
                }
        )

        StandardTextField(
            value = userLastname,
            hint = R.string.enter_last_name,
            label = R.string.label_lastname,
            isError = state.lastnameState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.lastnameState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.lastnameState,
                    errorMessageId = state.lastnameErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChanged = { userLastname = it },
            modifier = Modifier
                .focusRequester(emailRequester)
                .constrainAs(lastname) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(firstname.bottom, 15.dp)
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onValueChanged = { userEmail = it },
            modifier = Modifier
                .focusRequester(passwordRequester)
                .constrainAs(email) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(lastname.bottom, 15.dp)
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onTriggerEvent(
                    SignUpEvent.EmailPasswordAuthData(
                        firstname = userFirstname,
                        lastname = userLastname,
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

        TermsAndPrivacyAnnotatedText(
            onClickTerms = { onTriggerEvent(SignUpEvent.TermsAndConditions) },
            onClickPrivacy = { onTriggerEvent(SignUpEvent.PrivacyPolicy) },
            modifier = Modifier.constrainAs(termsService) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(password.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = {
                onTriggerEvent(
                    SignUpEvent.EmailPasswordAuthData(
                        firstname = userFirstname,
                        lastname = userLastname,
                        email = userEmail,
                        password = userPassword
                    )
                )
            },
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(termsService.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.auth_button_phone,
            onClick = { onTriggerNavigation(signUpPhone) },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(signUp.bottom, 5.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextSmall(
            text = R.string.or,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(or) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(phone.bottom, 10.dp)
                }
        )

        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
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
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
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
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.X)) },
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
fun SignUpPhoneScreen(
    state: StateFlow<BaseViewState<SignUpState>>,
    onPopBack: () -> Unit,
    onTriggerEvent: (SignUpEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit,
    onComplete: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<SignUpState>>().value
            if (currentState.isSignUpCompleted) {
                onComplete()
            } else {
                SignUpPhoneContent(
                    state = uiState.cast<BaseViewState.Data<SignUpState>>().value,
                    onTriggerEvent = onTriggerEvent,
                    onTriggerNavigation = onTriggerNavigation
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
fun SignUpPhoneContent(
    state: SignUpState,
    onTriggerEvent: (SignUpEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            signIn,
            welcome,
            message,
            firstname,
            lastname,
            phone,
            signUp,
            termsService,
            or,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(TOP)
        val secondTopGuideline = createGuidelineFromTop(SECOND_TOP)
        val startGuideline = createGuidelineFromStart(START)
        val endGuideline = createGuidelineFromEnd(END)

        var userFirstname by remember { mutableStateOf("") }
        var userLastname by remember { mutableStateOf("") }
        var userPhone by remember { mutableStateOf("") }

        val lastnameRequester = remember { FocusRequester() }
        val phoneRequester = remember { FocusRequester() }

        handleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

        SignInAnnotatedText(
            onClick = { onTriggerNavigation(signInEmail) },
            modifier = Modifier.constrainAs(signIn) {
                end.linkTo(endGuideline)
                top.linkTo(topGuideline)
            })

        StandardText(
            text = R.string.welcome_to_body_balance,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(secondTopGuideline)
                bottom.linkTo(message.top)
            }
        )

        StandardTextSmall(
            text = R.string.create_account_message,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            value = userFirstname,
            hint = R.string.enter_first_name,
            label = R.string.label_firstname,
            isError = state.firstnameState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.firstnameState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.firstnameState,
                    errorMessageId = state.firstnameErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChanged = { userFirstname = it },
            modifier = Modifier
                .focusRequester(lastnameRequester)
                .constrainAs(firstname) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(message.bottom, 15.dp)
                    width = Dimension.fillToConstraints
                }
        )

        StandardTextField(
            value = userLastname,
            hint = R.string.enter_last_name,
            label = R.string.label_lastname,
            isError = state.lastnameState == TextFieldState.ERROR,
            trailingIcon = { DisplayFieldState(state = state.lastnameState) },
            supportingText = {
                DisplayErrorMessage(
                    state = state.lastnameState,
                    errorMessageId = state.lastnameErrorMessage
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChanged = { userLastname = it },
            modifier = Modifier
                .focusRequester(phoneRequester)
                .constrainAs(lastname) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(firstname.bottom, 15.dp)
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onTriggerEvent(
                    SignUpEvent.PhoneAuthentication(
                        firstname = userFirstname,
                        lastname = userLastname,
                        phoneNumber = userPhone
                    )
                )
            }),
            onValueChanged = { userPhone = it },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(lastname.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        TermsAndPrivacyAnnotatedText(
            onClickTerms = { onTriggerEvent(SignUpEvent.TermsAndConditions) },
            onClickPrivacy = { onTriggerEvent(SignUpEvent.PrivacyPolicy) },
            modifier = Modifier.constrainAs(termsService) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = {
                onTriggerEvent(
                    SignUpEvent.PhoneAuthentication(
                        firstname = userFirstname,
                        lastname = userLastname,
                        phoneNumber = userPhone
                    )
                )
            },
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(termsService.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextSmall(
            text = R.string.or,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(or) {
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(signUp.bottom, 10.dp)
                }
        )

        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
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
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
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
            onClick = { onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.X)) },
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
fun handleAuthMethod(authMethod: AuthMethod, onTriggerEvent: (SignUpEvent) -> Unit) =
    when (authMethod) {
        AuthMethod.GOOGLE -> {
            GoogleBottomAuthSheet(
                onSignInResult = {
                                 if (it == null){
                                     Log.e("GoogleSignProcess", "Failed")
                                     onTriggerEvent(SignUpEvent.ThirdPartyAuthError(Throwable("Handle Auth Method")))
                                 }else{
                                     Log.e("GoogleSignProcess", "Success ${it.displayName}")
                                     onTriggerEvent(SignUpEvent.GoogleAuthentication)
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FacebookBottomAuthSheet(onTriggerEvent: (SignUpEvent) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

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
                Text(text = stringResource(id = R.string.auth_button_title_facebook))
                Button(
                    onClick = {
                        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.NONE))
                            }
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
fun XBottomAuthSheet(
    onTriggerEvent: (SignUpEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

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
                Text(text = stringResource(id = R.string.auth_button_title_x))
                Button(
                    onClick = {
                        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onTriggerEvent(SignUpEvent.SelectAuthMethod(AuthMethod.NONE))
                                FirebaseAuth.getInstance().signOut()
                            }
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.title_continue))
                }
            }
        }
    )
}