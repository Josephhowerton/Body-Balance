package com.fitness.authentication.signin.view

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.authentication.navigation.AuthEntryImpl
import com.fitness.authentication.signin.viewmodel.SignInEvent
import com.fitness.authentication.signin.viewmodel.SignInState
import com.fitness.authentication.util.DisplayErrorMessage
import com.fitness.authentication.util.DisplayFieldState
import com.fitness.authentication.util.NewNumberAnnotatedText
import com.fitness.authentication.util.SignUpForFreeAnnotatedText
import com.fitness.authentication.verification.PhoneVerification
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import auth.PhoneAuthState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.TextFieldState
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Light
@Dark
@Composable
private fun SignInPhonePreview() = BodyBalanceTheme {
    Surface {
        SignInPhoneContent(state = SignInState(), {}, {})
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
                SignInPhoneState(
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
private fun SignInPhoneState(
    state: SignInState,
    onTriggerEvent: (SignInEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit = {}
){
    val authState by remember { mutableStateOf(state.phoneAuthState) }

    if(authState is PhoneAuthState.CodeSent){
        PhoneVerification(
            authState = authState.cast(),
            codeState = state.codeState,
            errorMessage = state.codeErrorMessage,
            onVerify = { id, code -> onTriggerEvent(SignInEvent.VerifyPhoneAuthentication(id, code)) },
        )
    }
    else{
        SignInPhoneContent(state = state, onTriggerEvent=onTriggerEvent, onTriggerNavigation=onTriggerNavigation)
    }

}


@Composable
private fun SignInPhoneContent(
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
            cont,
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userPhone by remember { mutableStateOf("") }

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
            onClick = { onTriggerNavigation(AuthEntryImpl.signUp) },
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
            onClick = { onTriggerNavigation(AuthEntryImpl.resetPassword) },
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
            modifier = Modifier.constrainAs(cont) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(forgot.bottom, 25.dp)
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
//                    top.linkTo(cont.bottom)
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
