package com.fitness.component.components

import android.media.MediaPlayer
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import extensions.Dark
import extensions.Light
import kotlinx.coroutines.delay
import java.io.IOException

@Light
@Dark
@Composable
private fun TextItemUnSelectedPreview() = BodyBalanceTheme {
    Surface {
        TextItemUnSelected("Unselected")
    }
}

@Light
@Dark
@Composable
private fun TextItemSelectedPreview() = BodyBalanceTheme {
    Surface {
        TextItemSelected("Selected")
    }
}


@Composable
fun TextItemComponent(
    text: String,
    onClick:(String) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp
) {

    var isClicked by remember { mutableStateOf(false) }

    val rotationAnimatable = remember { Animatable(0f) }

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            rotationAnimatable.animateTo(
                targetValue = 360f, // Rotate 720 degrees
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        } else {
            rotationAnimatable.animateTo(
                targetValue = 0f, // Rotate 720 degrees
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable {
                isClicked = !isClicked
                onClick(text)
            }
            .wrapContentSize()
            .graphicsLayer {
                rotationY = rotationAnimatable.value
            }

    ) {
        if (rotationAnimatable.value < 90f) {
            TextItemUnSelected(text = text, size = size)
        } else if (rotationAnimatable.value > 270f) {
            TextItemSelected(text = text, size = size)
        }
    }

    if(rotationAnimatable.value > 20f && rotationAnimatable.value < 270f){
        TextItemBack(size = size)
    }

}

@Composable
fun TextItemUnSelected(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(5.dp),
        shape = RoundedCornerShape(5, 40, 5, 40),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(size)
                .padding(10.dp)

        ) {

            val (textRef, add) = createRefs()

            Text(text = text,
                modifier = Modifier.constrainAs(textRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(add.top)
                }
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier.constrainAs(add) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}

@Composable
fun TextItemBack(
    size: Dp = 120.dp
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(size)
    ) {
        WindIcon()
        PlayWavSound()
    }
}

@Composable
fun TextItemSelected(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
) {

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

    val color by animateColorAsState(
        targetValue = if (startAnimations) Green else MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(durationMillis = 500),
        label = "textColor"
    )

    LaunchedEffect(key1 = Unit) {
        delay(100)
        startAnimations = true
        offsetX = 0
        offsetY = 0
    }

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(5.dp),
        shape = RoundedCornerShape(40, 5, 40, 5),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(color = color)
                .size(size)
                .padding(10.dp)

        ) {

            val (textRef, checkMark) = createRefs()

            Text(text = text,
                modifier = Modifier.constrainAs(textRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(checkMark.top)
                }
            )

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier
                    .offset(x = animatedOffsetX, y = animatedOffsetY)
                    .constrainAs(checkMark) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@Composable
fun WindIcon() {
    val animatable = remember { Animatable(0f) }

    // Start the animation
    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = TweenSpec(durationMillis = 600) // Adjust duration as needed
        )
    }

    val context = LocalContext.current
    val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.icon_wind, context.theme)
    val bitmap = drawable?.toBitmap()?.asImageBitmap()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (bitmap != null) {
            Canvas(
                modifier = Modifier.size(150.dp) // Increase the size as needed
            ) {
                val iconWidth = size.width * animatable.value
                val centerX = (size.width - bitmap.width) / 2f
                val centerY = (size.height - bitmap.height) / 2f
                clipRect(left = 0f, top = 0f, right = iconWidth, bottom = size.height) {
                    drawImage(bitmap, topLeft = Offset(centerX, centerY))
                }
            }
        }
    }
}

@Composable
fun PlayWavSound() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer()
        try {
            val afd = context.resources.openRawResourceFd(R.raw.wind_sound) // Replace 'wind' with your file's name (without extension)
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()

            mediaPlayer.setOnPreparedListener {
                it.start()
            }
            mediaPlayer.prepareAsync()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        onDispose {
            mediaPlayer.release()
        }
    }
}

