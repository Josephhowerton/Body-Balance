package com.fitness.authentication.signup.view

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
import androidx.compose.ui.text.font.FontWeight
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
import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.signup.viewmodel.SignUpState
import com.fitness.authentication.util.DisplayErrorMessage
import com.fitness.authentication.util.DisplayFieldState
import com.fitness.authentication.util.PasswordTrailingIcon
import com.fitness.authentication.util.SignInAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.TextFieldState
import extensions.cast
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpEmailPreview() = BodyBalanceTheme {
    Surface {
        SignUpEmailContent(state = SignUpState())
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
            val failure = uiState.cast<BaseViewState.Error>().failure as AuthFailure

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
private fun SignUpEmailContent(
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
            phone,
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val logoBottomGuideline = createGuidelineFromTop(GuidelineProperties.SECOND_TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userFirstname by remember { mutableStateOf("") }
        var userLastname by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }

        var isPasswordVisible by remember { mutableStateOf(false) }

        val lastnameRequester = remember { FocusRequester() }
        val emailRequester = remember { FocusRequester() }
        val passwordRequester = remember { FocusRequester() }

        SignInAnnotatedText(
            onClick = { onTriggerNavigation(AuthEntryImpl.signInEmail) },
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
                top.linkTo(password.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.auth_button_phone,
            onClick = { onTriggerNavigation(AuthEntryImpl.signUpPhone) },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(signUp.bottom, 5.dp)
                width = Dimension.fillToConstraints
            }
        )
    }
}