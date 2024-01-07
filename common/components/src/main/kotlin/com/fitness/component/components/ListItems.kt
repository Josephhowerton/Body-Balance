package com.fitness.component.components

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.properties.STANDARD_TEXT_SIZE
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.EHealthLabel
import extensions.Dark
import extensions.Item
import extensions.Light
import kotlinx.coroutines.delay
import java.io.IOException


@Light
@Dark
@Composable
private fun PreviewList() = BodyBalanceTheme {
    Surface {
        BalanceLazyColumn(
            title = R.string.title_fitness_habits,
            description = R.string.desc_fitness_habits,
            items = EHealthLabel.values(),
            selections = mutableListOf(),
            onButtonClicked = { Log.e("Preview", it.toString())}
        )
    }
}


@Composable
fun <T: Item> BalanceLazyColumn(
    title: Int,
    description: Int,
    items: Array<T>,
    selections: MutableList<T>,
    modifier: Modifier = Modifier,
    onButtonClicked: (List<T>) -> Unit
) = ConstraintLayout(modifier = modifier.fillMaxSize()) {

    val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
    val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

    val (titleRef, listRef, okRef) = createRefs()
    val gridState = rememberLazyGridState()
    val isScrolling = gridState.isScrollInProgress
    LazyVerticalGrid(
        state = gridState,
        modifier = Modifier
            .constrainAs(listRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
        columns = GridCells.Fixed(3)
    ) {
        items(items) { item ->
            BalanceItem(
                item = item,
                selectedState = selections.isSelected(item),
                onClick = {
                    if (selections.contains(it)) {
                        selections.remove(it)
                    } else {
                        selections.add(it)
                    }
                }
            )
        }
    }

    AnimatedVisibility(
        visible = !isScrolling,
        modifier = Modifier.constrainAs(titleRef) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
    ) {
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                StandardTitleText(
                    text = title,
                    modifier = Modifier.padding()
                )

                Spacer(modifier = Modifier.size(10.dp))

                StandardTextSmall(
                    text = description,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding()
                )

                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }

    AnimatedVisibility(
        visible = !isScrolling,
        modifier = Modifier
            .shadow(elevation = 5.dp, shape = CircleShape)
            .constrainAs(okRef) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
    ) {
        Button(
            onClick = { onButtonClicked(selections) },
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape,
                )
                .constrainAs(okRef) {
                    end.linkTo(endGuide)
                    bottom.linkTo(bottomGuide)
                }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}
@Composable
private fun <T: Item> BalanceItem(
    item: T,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    selectedState: ItemState = ItemState.UNSELECTED
) {

    val animatable = remember { Animatable(0f) }
    var itemState by remember { mutableStateOf(selectedState) }

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
                onClick(item)
            }
            .wrapContentSize()
            .graphicsLayer {
                rotationY = animatable.value
            }

    ) {
        if (animatable.value < 70f) {
            TextItemUnSelected(
                title = item.title,
                desc = item.desc,
                size = size
            )
        } else if (animatable.value > 270f) {
            TextItemSelected(title = item.title, size = size)
        }

    }

    if (animatable.value > 40f && animatable.value < 270f) {
        TextItemTransition(size = size)
    }

}

@Composable
fun TextItemUnSelected(
    title: Int,
    modifier: Modifier = Modifier,
    desc: Int? = null,
    size: Dp = 120.dp,
) {
    var showInfo by remember { mutableStateOf(false) }
    var targetValue by remember { mutableIntStateOf(5) }

    val animateInt = animateIntAsState(
        targetValue = targetValue,
        label = "TextItemUnSelected",
        finishedListener = {
            targetValue = if (targetValue == 5) {
                40
            } else {
                5
            }
        }
    )

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(5.dp),
        shape = RoundedCornerShape(animateInt.value, 40, 5, 40),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(size)
                .padding(10.dp)
        ) {
            val (text, icon) = createRefs()

            desc?.let {
                TransitionalText(
                    title = stringResource(id = title),
                    desc = stringResource(id = desc),
                    showInfo = showInfo,
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(icon.top)
                    }
                )

                TransitionalIcon(
                    iconDefault = Icons.Default.Info,
                    iconTransition = Icons.Default.ArrowBack,
                    onTapInfo = { showInfo = it },
                    showInfo = showInfo,
                    modifier = Modifier.constrainAs(icon) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )

            } ?: run {
                Text(
                    text = stringResource(id = title),
                    modifier = Modifier
                        .constrainAs(text) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(icon.top)
                        }
                )

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.content_description_checkmark),
                    modifier = Modifier
                        .padding(5.dp)
                        .constrainAs(icon) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }


        }
    }
}

@Composable
private fun TransitionalText(
    title: String,
    desc: String,
    showInfo: Boolean,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        AnimatedVisibility(visible = !showInfo) {
            Text(text = title, modifier = Modifier)
        }
        AnimatedVisibility(visible = showInfo) {
            Text(
                text = desc,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                fontSize = STANDARD_TEXT_SIZE,
                modifier = modifier
            )
        }
    }

}

@Composable
private fun TransitionalIcon(
    iconDefault: ImageVector,
    iconTransition: ImageVector,
    showInfo: Boolean,
    modifier: Modifier,
    onTapInfo: (Boolean) -> Unit
) {

    var showInfoState by remember { mutableStateOf(showInfo) }

    Box(modifier = modifier) {
        AnimatedVisibility(visible = !showInfoState) {
            Icon(
                imageVector = iconDefault,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        showInfoState = !showInfoState
                        onTapInfo(showInfoState)

                    }
            )

        }
        AnimatedVisibility(visible = showInfoState) {
            Icon(
                imageVector = iconTransition,
                contentDescription = stringResource(id = R.string.content_description_checkmark),
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        showInfoState = !showInfoState
                        onTapInfo(showInfoState)
                    }
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
fun TextItemSelected(title: Int, modifier: Modifier = Modifier, size: Dp = 120.dp) {

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

            Text(text = stringResource(id = title),
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

fun <T: Item> List<Item>.isSelected(t: T)=
    if(this.contains(t)) {
    ItemState.SELECTED
} else {
    ItemState.UNSELECTED
}