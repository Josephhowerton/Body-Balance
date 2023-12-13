package com.fitness.authentication.signin.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.authentication.util.ForgotPasswordAnnotatedText
import com.fitness.authentication.util.SignUpForFreeAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardIconButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme


@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInEmailPreview() = BodyBalanceTheme {
    Surface {
        SignInEmailScreen()
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInPhonePreview() = BodyBalanceTheme {
    Surface {
        SignInPhoneScreen()
    }
}

@Composable
fun SignInEmailScreen(
    onClickSignUp: () -> Unit = {},
    onEmailFieldChange: (String) -> Unit = {},
    onPasswordFieldChange: (String) -> Unit = {},
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
            hint = R.string.enter_email,
            onValueChanged = onEmailFieldChange,
            modifier = Modifier.constrainAs(email) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            hint = R.string.enter_password,
            onValueChanged = onPasswordFieldChange,
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
            modifier = Modifier.constrainAs(signUp) {
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
                    top.linkTo(signUp.bottom)
                }
        )

        createHorizontalChain(phone, google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_email,
            desc = R.string.content_description_email,
            onClick = onClickPhone,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(phone) {
                    start.linkTo(startGuideline)
                    end.linkTo(google.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = onClickGoogle,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(google) {
                    start.linkTo(phone.end)
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

    }
}

@Composable
fun SignInPhoneScreen(
    onClickSignUp: () -> Unit = {},
    onEmailFieldChange: (String) -> Unit = {},
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
            hint = R.string.enter_phone,
            onValueChanged = onEmailFieldChange,
            modifier = Modifier.constrainAs(email) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        ForgotPasswordAnnotatedText(
            onClick = onClickForgotPassword,
            modifier = Modifier.constrainAs(forgot) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(email.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            modifier = Modifier.constrainAs(signUp) {
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
                    top.linkTo(signUp.bottom)
                }
        )

        createHorizontalChain(phone, google, facebook, x, chainStyle = ChainStyle.Packed)

        StandardIconButton(
            icon = R.drawable.icon_phone,
            desc = R.string.content_description_phone,
            onClick = onClickPhone,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(phone) {
                    start.linkTo(startGuideline)
                    end.linkTo(google.start)
                    top.linkTo(or.bottom)
                }
        )

        StandardIconButton(
            icon = R.drawable.icon_google_logo,
            desc = R.string.content_description_google,
            onClick = onClickGoogle,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(google) {
                    start.linkTo(phone.end)
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

    }
}
