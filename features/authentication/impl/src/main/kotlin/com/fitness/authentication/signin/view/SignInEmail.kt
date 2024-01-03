package com.fitness.authentication.signin.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import auth.AuthFailure
import com.fitness.authentication.navigation.AuthEntryImpl
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signInPhone
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUp
import com.fitness.authentication.signin.viewmodel.SignInEvent
import com.fitness.authentication.signin.viewmodel.SignInState
import com.fitness.authentication.util.DisplayErrorMessage
import com.fitness.authentication.util.DisplayFieldState
import com.fitness.authentication.util.ForgotPasswordAnnotatedText
import com.fitness.authentication.util.PasswordTrailingIcon
import com.fitness.authentication.util.SignUpForFreeAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.TextFieldState
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState


@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInEmailPreview() = BodyBalanceTheme {
    Surface {
        SignInEmailContent(state = SignInState(), {}, {})
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
            if (currentState.isAuthenticated) {
                onComplete()
            } else {
                SignInEmailContent(
                    state = currentState,
                    onTriggerEvent = onTriggerEvent,
                    onTriggerNavigation = onTriggerNavigation,
                )
            }
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as Failure

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
            cont,
            phone,
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        val passwordRequester = remember { FocusRequester() }

        HandleSignInMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
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
            onClick = {
                onTriggerEvent(
                    SignInEvent.EmailPasswordAuthentication(
                        email = userEmail,
                        password = userPassword
                    )
                )
            },
            modifier = Modifier.constrainAs(cont) {
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
                top.linkTo(cont.bottom, 5.dp)
                width = Dimension.fillToConstraints
            }
        )

//        StandardTextSmall(
//            text = R.string.or,
//            modifier = Modifier
//                .padding(20.dp)
//                .constrainAs(or) {
//                    start.linkTo(startGuideline)
//                    end.linkTo(endGuideline)
//                    top.linkTo(phone.bottom)
//                }
//        )
//
//        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)
//
//        StandardIconButton(
//            icon = R.drawable.icon_google_logo,
//            desc = R.string.content_description_google,
//            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.GOOGLE)) },
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(google) {
//                    start.linkTo(startGuideline)
//                    end.linkTo(facebook.start)
//                    top.linkTo(or.bottom, 10.dp)
//                }
//        )
//
//        StandardIconButton(
//            icon = R.drawable.icon_facebook_logo,
//            desc = R.string.content_description_facebook,
//            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.FACEBOOK)) },
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(facebook) {
//                    start.linkTo(google.end)
//                    end.linkTo(x.start)
//                    top.linkTo(or.bottom, 10.dp)
//                }
//        )
//
//        StandardIconButton(
//            icon = R.drawable.icon_x_logo,
//            desc = R.string.content_description_x,
//            onClick = { onTriggerEvent(SignInEvent.SelectAuthMethod(AuthMethod.X)) },
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(x) {
//                    start.linkTo(facebook.end)
//                    end.linkTo(endGuideline)
//                    top.linkTo(or.bottom, 10.dp)
//                }
//        )
    }
}
