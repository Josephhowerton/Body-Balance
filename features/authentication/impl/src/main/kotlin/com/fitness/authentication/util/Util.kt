package com.fitness.authentication.util

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.resources.R
import com.fitness.theme.ui.Green
import com.fitness.theme.ui.Red
import com.fitness.theme.ui.primaryVariantHub
import extensions.TextFieldState

fun verifyName(name: String): Pair<TextFieldState, Int> {
    if (name.isBlank()) {
        return TextFieldState.PENDING to 0 // 0 indicates no error message
    }

    return if (name.length >= 2) {
        TextFieldState.OK to 0
    } else {
        TextFieldState.ERROR to R.string.auth_name_length_error
    }
}

fun verifyEmail(email: String): Pair<TextFieldState, Int> {
    if (email.isBlank()) {
        return TextFieldState.PENDING to 0
    }

    return if (email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
        TextFieldState.OK to 0
    } else {
        TextFieldState.ERROR to R.string.auth_invalid_email_format
    }
}

fun verifyPhone(phone: String): Pair<TextFieldState, Int> {
    if (phone.isBlank()) {
        return TextFieldState.PENDING to 0
    }

    return if (Patterns.PHONE.matcher(phone).matches()) {
        TextFieldState.OK to 0
    } else {
        TextFieldState.ERROR to R.string.auth_invalid_phone_format
    }
}

fun verifyPassword(password: String): Pair<TextFieldState, Int> {
    if (password.isBlank()) {
        return TextFieldState.PENDING to 0
    }

    val errorMessages = mutableListOf<Int>()

    if (!password.matches(".*[a-z].*".toRegex())) {
        errorMessages.add(R.string.auth_password_lowercase_error)
    }
    if (!password.matches(".*[A-Z].*".toRegex())) {
        errorMessages.add(R.string.auth_password_uppercase_error)
    }
    if (!password.matches(".*\\d.*".toRegex())) {
        errorMessages.add(R.string.auth_password_digit_error)
    }
    if (!password.matches(".*[@$!%*?&].*".toRegex())) {
        errorMessages.add(R.string.auth_password_special_char_error)
    }
    if (password.length < 8) {
        errorMessages.add(R.string.auth_password_length_error)
    }

    return if (errorMessages.isEmpty()) {
        TextFieldState.OK to 0
    } else {
        // Assuming you want to return the first error encountered
        TextFieldState.ERROR to errorMessages.first()
    }
}

@Composable
fun PasswordTrailingIcon(state: TextFieldState, isVisible: Boolean){
    Row {
        DisplayPassword(isVisible = isVisible)
        DisplayCheckmark(state = state)
    }
}

@Composable
fun DisplayPassword(isVisible: Boolean) =
    if(isVisible){
        Icon(
            painter = painterResource(id = R.drawable.icon_open_eye),
            contentDescription = stringResource(id = R.string.content_description_open_eye),
            tint = Green
        )
    }else{
        Icon(
            painter = painterResource(id = R.drawable.icon_closed_eye),
            contentDescription = stringResource(id = R.string.content_description_closed_eye),
            tint = Green
        )
    }

@Composable
fun DisplayCheckmark(state: TextFieldState) =
    when (state) {
        TextFieldState.PENDING -> {}
        TextFieldState.OK -> {
            Icon(
                painter = painterResource(id = R.drawable.icon_checkmark),
                contentDescription = stringResource(id = R.string.content_description_email),
                tint = Green
            )
        }
        TextFieldState.ERROR -> {
            Icon(
                painter = painterResource(id = R.drawable.icon_error),
                contentDescription = stringResource(id = R.string.content_description_email),
                tint = Red
            )
        }
    }

@Composable
fun DisplayErrorMessage(state: TextFieldState, errorMessageId: Int?) {
    if (state == TextFieldState.ERROR) {
        errorMessageId?.let {
            Text(
                text = stringResource(id = errorMessageId),
                color = Red
            )
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpAnnotatedText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.do_not_have_an_account_1_2))
        append(" ")

        // Create a clickable part of the text
        pushStringAnnotation(tag = "signUp", annotation = "signUp")
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(stringResource(id = R.string.do_not_have_an_account_2_2))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "signUp", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignInAnnotatedText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.already_have_an_account_1_2))
        append(" ")

        // Create a clickable part of the text
        pushStringAnnotation(tag = "signIn", annotation = "signIn")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.already_have_an_account_2_2))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "signIn", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TermsAndPrivacyAnnotatedText(
    modifier: Modifier = Modifier,
    onClickTerms: () -> Unit = {},
    onClickPrivacy: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.terms_and_privacy_1_4))
        append(" ")

        // Create a clickable part of the text
        pushStringAnnotation(tag = "terms", annotation = "terms")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.terms_and_privacy_2_4))
        }
        pop()

        append(" ")
        append(stringResource(id = R.string.terms_and_privacy_3_4))
        append(" ")

        pushStringAnnotation(tag = "privacy", annotation = "privacy")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.terms_and_privacy_4_4))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClickTerms()
                }

            annotatedString.getStringAnnotations(tag = "privacy", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClickPrivacy()
                }
        }
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ForgotPasswordAnnotatedText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        // Create a clickable part of the text
        pushStringAnnotation(tag = "forgotPassword", annotation = "forgotPassword")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.forgot_password))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "forgotPassword", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewNumberAnnotatedText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        // Create a clickable part of the text
        pushStringAnnotation(tag = "newNumber", annotation = "newNumber")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.new_number))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "newNumber", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpForFreeAnnotatedText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        // Create a clickable part of the text
        pushStringAnnotation(tag = "signUpForFree", annotation = "signUpForFree")
        withStyle(style = SpanStyle(color = primaryVariantHub)) {
            append(stringResource(id = R.string.sign_up_for_free))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "signUpForFree", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}