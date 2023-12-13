package com.fitness.authentication.util

import android.content.res.Configuration
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.resources.R

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
        withStyle(style = SpanStyle(color = Color.Blue)) {
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
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(stringResource(id = R.string.terms_and_privacy_2_4))
        }
        pop()

        append(" ")
        append(stringResource(id = R.string.terms_and_privacy_3_4))
        append(" ")

        pushStringAnnotation(tag = "privacy", annotation = "privacy")
        withStyle(style = SpanStyle(color = Color.Blue)) {
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
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(stringResource(id = R.string.do_not_have_an_account_2_2))
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