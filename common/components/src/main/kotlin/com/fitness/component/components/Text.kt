package com.fitness.component.components

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.fitness.resources.R
import com.fitness.component.properties.HEADLINE_TEXT_SIZE
import com.fitness.component.properties.STANDARD_TEXT_SIZE
import com.fitness.component.properties.STANDARD_TEXT_SIZE_SMALL
import com.fitness.component.properties.TITLE_TEXT_SIZE
import extensions.Dark
import extensions.Light


@Light
@Composable
private fun StandardTitleTextPreview() = Surface {
    StandardTitleText()
}

@Light
@Composable
private fun StandardHeadlineTextPreview() = Surface {
    StandardHeadlineText()
}

@Light
@Composable
private fun StandardTextPreview() = Surface {
    StandardText()
}

@Light
@Composable
private fun StandardTextSmallPreview() = Surface {
    StandardTextSmall()
}

@Composable
fun StandardTitleText(
    modifier: Modifier = Modifier,
    text: Int = R.string.create_account_message
){
    Text(
        text = stringResource(text),
        textAlign = TextAlign.Center,
        fontSize = TITLE_TEXT_SIZE,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}


@Composable
fun StandardHeadlineText(
    modifier: Modifier = Modifier,
    text: Int = R.string.create_account_message
){
    Text(
        text = stringResource(text),
        textAlign = TextAlign.Center,
        fontSize = HEADLINE_TEXT_SIZE,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun StandardText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Normal,
    text: Int = R.string.create_account_message
){
    Text(
        text = stringResource(text),
        textAlign = textAlign,
        fontSize = STANDARD_TEXT_SIZE,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun StandardTextSmall(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Normal,
    text: Int = R.string.create_account_message
){
    Text(
        text = stringResource(text),
        textAlign = textAlign,
        fontSize = STANDARD_TEXT_SIZE_SMALL,
        fontWeight = fontWeight,
        modifier = modifier
    )
}