package com.fitness.authentication.signin.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.authentication.signin.viewmodel.SignInEvent
import com.fitness.authentication.signin.viewmodel.SignInState
import com.fitness.authentication.util.ForgotPasswordAnnotatedText
import com.fitness.authentication.util.NewNumberAnnotatedText
import com.fitness.authentication.util.SignUpForFreeAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardIconButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.EmptyScreen
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.cast
import failure.AuthFailure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState


@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInEmailPreview() = BodyBalanceTheme {
    Surface {
        SignInEmailContent(state = SignInState())
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInPhonePreview() = BodyBalanceTheme {
    Surface {
        SignInPhoneContent(state = SignInState())
    }
}

@Composable
fun SignInEmailScreen(
    state: StateFlow<BaseViewState<SignInState>> = MutableStateFlow(BaseViewState.Data(SignInState())),
    verifyCredentials: (SignInEvent) -> Unit,
    onErrorEvent: (AuthFailure) -> Unit = {},
    onClickSignUp: () -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickForgotPassword: () -> Unit = {},
    onClickPhone :() -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Empty -> {
            EmptyScreen()
        }
        is BaseViewState.Data -> {
            SignInEmailContent(
                state = uiState.cast<BaseViewState.Data<SignInState>>().value,
                verifyCredentials = verifyCredentials,
                onClickSignUp = onClickSignUp,
                onClickContinue = onClickContinue,
                onClickForgotPassword = onClickForgotPassword,
                onClickPhone = onClickPhone,
                onClickGoogle = onClickGoogle,
                onClickFacebook = onClickFacebook,
                onClickX = onClickX
            )
        }
        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onErrorEvent.invoke(failure)
            }
        }
        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {}
    }

}

@Composable
fun SignInEmailContent(
    state: SignInState,
    verifyCredentials: (SignInEvent) -> Unit = {},
    onClickSignUp: () -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickForgotPassword: () -> Unit = {},
    onClickPhone :() -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            welcome,
            message,
            email,
            password,
            forgot,
            signUp,
            or,
            phone,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        StandardText(
            text = R.string.not_a_member,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(message.top)
            }
        )

        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }

        SignUpForFreeAnnotatedText(
            onClick = onClickSignUp,
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
            onValueChanged = {userEmail = it},
            trailingIcon = { },
            modifier = Modifier.constrainAs(email) {
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
            onValueChanged = {userPassword = it},
            modifier = Modifier.constrainAs(password) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(email.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        ForgotPasswordAnnotatedText(
            onClick = onClickForgotPassword,
            modifier = Modifier.constrainAs(forgot) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(password.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            enabled = state.isEmailVerified,
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(forgot.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.auth_button_phone,
            onClick = onClickPhone,
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
            onClick = onClickGoogle,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(google) {
                    start.linkTo(startGuideline)
                    end.linkTo(facebook.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_facebook_logo,
            desc = R.string.content_description_facebook,
            onClick = onClickFacebook,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(facebook) {
                    start.linkTo(google.end)
                    end.linkTo(x.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_x_logo,
            desc = R.string.content_description_x,
            onClick = onClickX,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom)
                }
        )

        LaunchedEffect(userEmail, userPassword) {
            verifyCredentials(SignInEvent.EmailPasswordAuthData(userEmail, userPassword))
        }
    }
}

@Composable
fun SignInPhoneScreen(
    state: StateFlow<BaseViewState<SignInState>> = MutableStateFlow(BaseViewState.Data(SignInState())),
    onErrorEvent: (AuthFailure) -> Unit,
    verifyCredentials: (SignInEvent) -> Unit,
    onClickSignUp: () -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickForgotPassword: () -> Unit = {},
    onClickEmail :() -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Empty -> {
            EmptyScreen()
        }
        is BaseViewState.Data -> {
            SignInPhoneContent(
                state = uiState.cast<BaseViewState.Data<SignInState>>().value,
                verifyCredentials = verifyCredentials,
                onClickSignUp = onClickSignUp,
                onClickContinue = onClickContinue,
                onClickForgotPassword = onClickForgotPassword,
                onClickEmail = onClickEmail,
                onClickGoogle = onClickGoogle,
                onClickFacebook = onClickFacebook,
                onClickX = onClickX
            )
        }
        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onErrorEvent.invoke(failure)
            }
        }
        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {}
    }
}

@Composable
fun SignInPhoneContent(
    state: SignInState,
    verifyCredentials: (SignInEvent) -> Unit = {},
    onClickSignUp: () -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickForgotPassword: () -> Unit = {},
    onClickEmail :() -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            welcome,
            message,
            phone,
            email,
            forgot,
            signUp,
            or,
            google,
            facebook,
            x
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userPhone by remember { mutableStateOf("") }

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
            onClick = onClickSignUp,
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
            onValueChanged = {userPhone = it},
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        NewNumberAnnotatedText(
            onClick = onClickForgotPassword,
            modifier = Modifier.constrainAs(forgot) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            enabled = state.isEmailVerified,
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(forgot.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.auth_button_title_email,
            onClick = onClickEmail,
            modifier = Modifier.constrainAs(email) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(signUp.bottom, 5.dp)
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
                    top.linkTo(email.bottom)
                }
        )

        createHorizontalChain(google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = onClickGoogle,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(google) {
                    start.linkTo(startGuideline)
                    end.linkTo(facebook.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_facebook_logo,
            desc = R.string.content_description_facebook,
            onClick = onClickFacebook,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(facebook) {
                    start.linkTo(google.end)
                    end.linkTo(x.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_x_logo,
            desc = R.string.content_description_x,
            onClick = onClickX,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom)
                }
        )

        LaunchedEffect(userPhone) {
            verifyCredentials(SignInEvent.PhoneAuthData(userPhone))
        }
    }
}