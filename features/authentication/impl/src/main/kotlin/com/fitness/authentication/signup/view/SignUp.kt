package com.fitness.authentication.signup.view

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import auth.AuthFailure
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signInEmail
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUpEmail
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUpPhone
import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.signup.viewmodel.SignUpState
import com.fitness.authentication.util.AuthMethod
import com.fitness.authentication.util.SignInAnnotatedText
import com.fitness.component.components.BodyBalanceImageLogo
import com.fitness.component.components.StandardHeadlineText
import com.fitness.component.components.StandardOutlinedIconButton
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties.END
import com.fitness.component.properties.GuidelineProperties.LOGO_BOTTOM
import com.fitness.component.properties.GuidelineProperties.LOGO_TOP
import com.fitness.component.properties.GuidelineProperties.START
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpLitePreview() {
    BodyBalanceTheme {
        Surface {
            SignUpLiteContent(state = SignUpState(), onTriggerEvent = {}, onTriggerNavigation = {})
        }
    }
}

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
            SignUpLiteContent(
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
fun SignUpLiteContent(
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
            login,
        ) = createRefs()

        val logoTopGuideline = createGuidelineFromTop(LOGO_TOP)
        val logoBottomGuideline = createGuidelineFromTop(LOGO_BOTTOM)
        val startGuideline = createGuidelineFromStart(START)
        val endGuideline = createGuidelineFromEnd(END)

        HandleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

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
                bottom.linkTo(login.top)
                width = Dimension.fillToConstraints
            }
        )

        SignInAnnotatedText(
            onClick = { onTriggerNavigation(signInEmail) },
            modifier = Modifier.constrainAs(login) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 25.dp)
            })
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

        HandleAuthMethod(authMethod = state.authMethod, onTriggerEvent = onTriggerEvent)

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
            onClick = { onTriggerNavigation(signUpEmail) },
            modifier = Modifier.constrainAs(login) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(twitter.bottom, 25.dp)
            })
    }
}


@Composable
fun HandleAuthMethod(authMethod: AuthMethod, onTriggerEvent: (SignUpEvent) -> Unit) =
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