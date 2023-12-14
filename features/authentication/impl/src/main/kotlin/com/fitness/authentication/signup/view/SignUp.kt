package com.fitness.authentication.signup.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.authentication.signup.viewmodel.SignUpEvent
import com.fitness.authentication.signup.viewmodel.SignUpState
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
private fun SignUpPreview() {
    BodyBalanceTheme {
        Surface {
            SignUpContent()
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
    onErrorEvent: (AuthFailure) -> Unit,
    onClickEmail: () -> Unit = {},
    onClickPhone: () -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {},
    onClickAnnotatedText: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Empty -> {
            EmptyScreen()
        }
        is BaseViewState.Data -> {
            SignUpContent(
                onClickEmail = onClickEmail,
                onClickPhone = onClickPhone,
                onClickGoogle = onClickGoogle,
                onClickFacebook = onClickFacebook,
                onClickX = onClickX,
                onClickAnnotatedText = onClickAnnotatedText
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

        else -> {

        }
    }
}

@Composable
fun SignUpContent(
    onClickEmail: () -> Unit = {},
    onClickPhone: () -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {},
    onClickAnnotatedText: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (logo,
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
            onClick = onClickEmail,
            modifier = Modifier.constrainAs(email) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom,  15.dp)
                bottom.linkTo(phone.top)
                width = Dimension.fillToConstraints
            }
        )

        StandardOutlinedIconButton(
            icon = R.drawable.icon_phone,
            desc = R.string.content_description_phone,
            text = R.string.auth_button_phone,
            onClick = onClickPhone,
            modifier = Modifier.constrainAs(phone){
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
            onClick = onClickGoogle,
            modifier = Modifier.constrainAs(google){
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
            onClick = onClickFacebook,
            modifier = Modifier.constrainAs(facebook){
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
            onClick = onClickX,
            modifier = Modifier.constrainAs(twitter){
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(facebook.bottom, 10.dp)
                bottom.linkTo(login.top)
                width = Dimension.fillToConstraints
            }
        )

        SignInAnnotatedText(
            onClick = onClickAnnotatedText,
            modifier = Modifier.constrainAs(login) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(twitter.bottom, 25.dp)
            })
    }
}


@Composable
fun SignUpEmailScreen(
    state: StateFlow<BaseViewState<SignUpState>> = MutableStateFlow(BaseViewState.Data(SignUpState())),
    onErrorEvent: (AuthFailure) -> Unit,
    verifyCredentials: (SignUpEvent) -> Unit,
    onClickContinue: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacy: () -> Unit = {},
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
            SignUpEmailContent(
                state = uiState.cast<BaseViewState.Data<SignUpState>>().value,
                verifyCredentials = verifyCredentials,
                onClickContinue = onClickContinue,
                onClickTerms = onClickTerms,
                onClickPrivacy = onClickPrivacy,
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
fun SignUpEmailContent(
    state: SignUpState,
    verifyCredentials: (SignUpEvent) -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacy: () -> Unit = {},
    onClickPhone :() -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
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

        SignInAnnotatedText(
            onClick = onClickTerms,
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
            onValueChanged = { userFirstname = it },
            modifier = Modifier.constrainAs(firstname) {
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
            onValueChanged = { userLastname = it },
            modifier = Modifier.constrainAs(lastname) {
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
            onValueChanged = { userEmail = it },
            modifier = Modifier.constrainAs(email) {
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
            onValueChanged = { userPassword = it },
            modifier = Modifier.constrainAs(password) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(email.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        TermsAndPrivacyAnnotatedText(
            onClickTerms = onClickTerms,
            onClickPrivacy = onClickPrivacy,
            modifier = Modifier.constrainAs(termsService) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(password.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            enabled = state.isVerified,
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(termsService.bottom, 25.dp)
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
            onClick = onClickGoogle,
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
            onClick = onClickFacebook,
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
            onClick = onClickX,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        LaunchedEffect(userFirstname, userLastname, userEmail, userPassword) {
            verifyCredentials(SignUpEvent.EmailPasswordAuthData(userFirstname, userLastname, userEmail, userPassword))
        }
    }
}


@Composable
fun SignUpPhoneScreen(
    state: StateFlow<BaseViewState<SignUpState>> = MutableStateFlow(BaseViewState.Data(SignUpState())),
    onErrorEvent: (AuthFailure) -> Unit,
    verifyCredentials: (SignUpEvent) -> Unit,
    onClickContinue: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacy: () -> Unit = {},
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
            SignUpPhoneContent(
                state = uiState.cast<BaseViewState.Data<SignUpState>>().value,
                verifyCredentials = verifyCredentials,
                onClickContinue = onClickContinue,
                onClickTerms = onClickTerms,
                onClickPrivacy = onClickPrivacy,
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
fun SignUpPhoneContent(
    state: SignUpState,
    verifyCredentials: (SignUpEvent) -> Unit = {},
    onClickContinue: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacy: () -> Unit = {},
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    onClickX: () -> Unit = {}
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


        SignInAnnotatedText(
            onClick = onClickTerms,
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
            onValueChanged = {userFirstname = it},
            modifier = Modifier.constrainAs(firstname) {
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
            onValueChanged = {userLastname = it},
            modifier = Modifier.constrainAs(lastname) {
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
            onValueChanged = {userPhone = it},
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(lastname.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        TermsAndPrivacyAnnotatedText(
            onClickTerms = onClickTerms,
            onClickPrivacy = onClickPrivacy,
            modifier = Modifier.constrainAs(termsService) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            enabled = state.isVerified,
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
            onClick = onClickGoogle,
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
            onClick = onClickFacebook,
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
            onClick = onClickX,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(x) {
                    start.linkTo(facebook.end)
                    end.linkTo(endGuideline)
                    top.linkTo(or.bottom, 10.dp)
                }
        )

        LaunchedEffect(userFirstname, userLastname, userPhone) {
            verifyCredentials(SignUpEvent.PhoneAuthData(userFirstname, userLastname, userPhone))
        }
    }
}