package com.fitness.component.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import extensions.Dark
import extensions.Light
import kotlinx.coroutines.delay


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

@Composable
fun AddToPlannerButton(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(5f),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_add_more),
                contentDescription = stringResource(id = R.string.content_description_icon_add),
                modifier = Modifier
                    .padding(3.dp)
                    .size(16.dp)
            )

            Text(text = stringResource(id = R.string.title_planner))
        }
    }
}

@Composable
fun TransitionButton(modifier: Modifier = Modifier) {
    val color by animateColorAsState(
        targetValue = Green,
        animationSpec = tween(durationMillis = 250),
        label = "textColor"
    )

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(40f),
        modifier = modifier.size(50.dp)
    ) {}
}

@Composable
fun CheckmarkConfirmButton(modifier: Modifier = Modifier) {
    var startAnimations by remember { mutableStateOf(false) }

    var offsetX by remember { mutableIntStateOf(-50) }
    var offsetY by remember { mutableIntStateOf(50) }

    val animatedOffsetX by animateDpAsState(
        targetValue = offsetX.dp,
        label = "animatedOffsetX",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val animatedOffsetY by animateDpAsState(
        targetValue = offsetY.dp,
        label = "animatedOffsetY",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    LaunchedEffect(key1 = Unit) {
        delay(100)
        startAnimations = true
        offsetX = 0
        offsetY = 0
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Green),
        shape = CircleShape,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.icon_checkmark),
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier
                    .padding(10.dp)
                    .offset(x = animatedOffsetX, y = animatedOffsetY)
            )
        }
    }
}

