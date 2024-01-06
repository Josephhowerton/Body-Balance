package com.fitness.authentication.verification

import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import auth.PhoneAuthState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.TextFieldState

@Light
@Dark
@Composable
private fun PhoneVerificationContentPreview() = BodyBalanceTheme {
    Surface {
        PhoneVerification()
    }
}

@Composable
fun PhoneVerification(
    authState: PhoneAuthState.CodeSent = PhoneAuthState.CodeSent(""),
    codeState: TextFieldState = TextFieldState.OK,
    errorMessage: Int? = null,
    onVerify: (String, String) -> Unit = { _, _ -> }
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        var resetFields by remember { mutableStateOf(codeState) }
        val digits = remember { Array(6) { mutableStateOf("") } }

        val (title, message, code, error, verify) = createRefs()


        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val bottomGuideline = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        digits.forEach {
            if (isCodeInvalid(resetFields)) {
                it.value = ""
                resetFields = TextFieldState.OK
            }
        }
        StandardText(
            text = R.string.code_verification,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(message.top, 10.dp)
            }
        )

        StandardTextSmall(
            text = R.string.code_verification_description,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(title.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        Row(
            horizontalArrangement = SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .constrainAs(code) {
                    end.linkTo(endGuideline)
                    start.linkTo(startGuideline)
                    top.linkTo(message.bottom, 20.dp)
                }
        ) {
            digits.forEachIndexed { index, digit ->
                val focusManager = LocalFocusManager.current

                OutlinedTextField(
                    value = digit.value,
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    onValueChange = {
                        handleDigitInput(index, it, focusManager, digits)
                    },
                    singleLine = true,
                    isError = isCodeInvalid(codeState),
                    modifier = Modifier
                        .width(50.dp)
                        .onKeyEvent {
                            if (it.key == Key.Backspace && digit.value.isEmpty() && index > 0) {
                                digits[index - 1].value = ""
                                focusManager.moveFocus(FocusDirection.Previous)
                                true
                            } else {
                                false
                            }
                        },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        if(isCodeInvalid(codeState)){
            errorMessage?.let {
                StandardTextSmall(
                    text = errorMessage,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.constrainAs(error) {
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideline)
                        top.linkTo(code.bottom, 10.dp)
                        width = Dimension.fillToConstraints
                    }
                )
            }
        }

        Button(
            onClick = {
                onVerify(
                    authState.verificationId,
                    digits.joinToString(separator = "") { it.value })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .constrainAs(verify) {
                    bottom.linkTo(bottomGuideline)
                }
        ) {
            Text(stringResource(R.string.verify))
        }
    }
}

fun handleDigitInput(
    index: Int,
    digit: String,
    focusManager: FocusManager,
    digits: Array<MutableState<String>>
) {
    val filteredDigit = digit.filter { it.isDigit() }

    if (filteredDigit.length <= 1) {
        digits[index].value = filteredDigit
        if (filteredDigit.length == 1 && index < 5) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }
}

fun isCodeInvalid(state: TextFieldState) = state == TextFieldState.ERROR
