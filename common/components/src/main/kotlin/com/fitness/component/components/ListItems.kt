package com.fitness.component.components

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.EHealthLabel
import extensions.Dark
import extensions.Light
import kotlinx.coroutines.delay
import java.io.IOException


@Light
@Dark
@Composable
private fun PreviewContent() = ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    val list = createRef()

    val gridState = rememberLazyGridState()
    val goals = remember { mutableListOf<String>() }

    LazyVerticalGrid(
        state = gridState,
        modifier = Modifier
            .constrainAs(list) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
        columns = GridCells.Fixed(3)
    ) {
        items(EHealthLabel.values()) { label ->
            TextItem(
                text = stringResource(id = label.title),
                onClick = {
                    if (goals.contains(it)) {
                        goals.remove(it)
                    } else {
                        goals.add(it)
                    }
                }
            )
        }
    }
}

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
fun TextItem(
    text: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
) {

    val animatable = remember { Animatable(0f) }
    var itemState by remember { mutableStateOf(ItemState.UNSELECTED) }

    LaunchedEffect(key1 = itemState) {
        when (itemState) {
            ItemState.SELECTED -> {
                animatable.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            }

            else -> {
                animatable.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable {
                itemState = itemState.onItemClickState()
                onClick(text)
            }
            .wrapContentSize()
            .graphicsLayer {
                rotationY = animatable.value
            }

    ) {
        if (animatable.value < 70f) {
            TextItemUnSelected(
                text = text,
                size = size
            )
        } else if (animatable.value > 270f) {
            TextItemSelected(text = text, size = size)
        }

    }

    if (animatable.value > 40f && animatable.value < 270f) {
        TextItemTransition(size = size)
    }

}

@Composable
fun TextItemUnSelected(
    title: String,
    desc: String,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
) {
    var isTitleVisible = MutableTransitionState(initialState = true)
    var targetValue by remember { mutableIntStateOf(5) }

    val animateInt = animateIntAsState(
        targetValue = targetValue,
        label = "TextItemUnSelected",
        finishedListener = {
            targetValue = if(targetValue == 5){
                40
            } else{
                5
            }
        }
    )

    Card(
        modifier = modifier.wrapContentSize().padding(5.dp),
        shape = RoundedCornerShape(animateInt.value, 40, 5, 40),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(size)
                .padding(10.dp)
        ) {
            val (text, icon) = createRefs()

            TransitionalText(
                title = title,
                desc = desc,
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(icon.top)
                }
            )

            TransitionalIcon(
                iconDefault = Icons.Default.Info,
                iconTransition = Icons.Default.ArrowBack,
                modifier = Modifier.constrainAs(icon) {
                    end.linkTo(parent.end)
                    bottom.linkTo(icon.top)
                }
            )
        }
    }
}

@Composable
private fun TransitionalText(
    title: String,
    desc: String,
    showInfo: Boolean,
    modifier: Modifier,
    onTapInfo: (Boolean) -> Unit
){
    val showInfoState by remember{ mutableStateOf(showInfo) }
    Box(modifier = modifier) {
        AnimatedVisibility(visible = !showInfoState) {
            Text(text = title, modifier = Modifier.clickable{ onTapInfo()})

        }
        AnimatedVisibility(visible = showInfoState) {
            Text(text = desc)

        }
    }

}

@Composable
private fun TransitionalIcon(
    iconDefault: ImageVector,
    iconTransition: ImageVector,
    showInfo: Boolean,
    modifier: Modifier
){

    val showInfoState by remember{ mutableStateOf(showInfo) }
    Box(modifier = modifier) {
        AnimatedVisibility(visible = !showInfoState) {
            Icon(
                imageVector = iconDefault,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier.padding(5.dp)
            )

        }
        AnimatedVisibility(visible = showInfoState) {
            Icon(
                imageVector = iconTransition,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun TextItemTransition(size: Dp = 120.dp) {
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
fun TextItemSelected(text: String, modifier: Modifier = Modifier, size: Dp = 120.dp) {

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
    val drawable =
        ResourcesCompat.getDrawable(context.resources, R.drawable.icon_wind, context.theme)
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
            val afd =
                context.resources.openRawResourceFd(R.raw.wind_sound) // Replace 'wind' with your file's name (without extension)
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

private fun isInfoAvailable(desc: String?, icon: ImageVector?): Boolean =
    desc != null && icon == Icons.Default.Info


enum class ItemState {
    SELECTED,
    UNSELECTED
}

fun ItemState.onItemClickState() =
    if (this == ItemState.SELECTED) {
        ItemState.UNSELECTED
    } else {
        ItemState.SELECTED
    }