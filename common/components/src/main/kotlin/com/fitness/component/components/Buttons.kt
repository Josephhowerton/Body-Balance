package com.fitness.component.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light


@Dark
@Light
@Composable
private fun StandardIconButtonPreview() {
    BodyBalanceTheme {
        Surface {
            StandardIconButton(
                R.drawable.icon_google_logo,
                R.string.content_description_google
            )
        }
    }
}

@Dark
@Light
@Composable
private fun StandardOutlinedButtonPreview() {
    BodyBalanceTheme {
        Surface {
            StandardOutlinedIconButton(
                icon = R.drawable.icon_google_logo,
                desc = R.string.content_description_google,
                text =  R.string.content_description_google
            )
        }
    }
}

@Dark
@Light
@Composable
private fun StandardButtonPreview() {
    BodyBalanceTheme {
        Surface {
            StandardButton(
                text =  R.string.content_description_google,
                enabled = true,
            )
        }
    }
}

@Composable
fun StandardIconButton(
    icon: Int,
    desc: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = desc),
            tint = Color.Unspecified
        )
    }
}

@Composable
fun StandardOutlinedIconButton(
    icon: Int,
    desc: Int,
    text: Int,
    modifier: Modifier = Modifier,
    iconSize: Int = 24,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(modifier = Modifier.padding(2.dp)) {
            Icon(
                painterResource(id = icon),
                contentDescription = stringResource(id = desc),
                modifier = Modifier.size(iconSize.dp),
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(8.dp))
            Text(stringResource(id = text))
        }
    }
}

@Composable
fun StandardButton(
    text: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
    ) {
        Row {
            Text(stringResource(id = text))
        }
    }
}

@Composable
fun ContinueButton(onClick: () -> Unit = {}){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        onClick = { onClick() }
    ) {
        Text(text = stringResource(id = R.string.title_continue))
    }
}

