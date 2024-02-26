package com.fitness.component

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.OffBlack
import com.fitness.theme.ui.OffWhite
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun HorizontalRulerPreview() = BodyBalanceTheme {
    Surface {
        HorizontalRuler(steps = 1000, onValueChanged = {})
    }
}

@Light
@Dark
@Composable
private fun VerticalRulerPreview() = BodyBalanceTheme {
    Surface {
        VerticalRuler(steps = 1000, onValueChanged = {})
    }
}

@Composable
fun VerticalRuler(
    steps: Int,
    modifier: Modifier = Modifier,
    onValueChanged: (Float) -> Unit
) {
    BoxWithConstraints(
        contentAlignment = Alignment.CenterEnd,
        modifier = modifier.fillMaxHeight()
    ) {
        val contentColor: Color = if(isSystemInDarkTheme()) OffWhite else OffBlack
        val centerOfScreen = constraints.maxHeight / 2.0

        VerticalRuler(
            steps = steps,
            centerOfScreen = centerOfScreen,
            contentColor = contentColor,
            onValueChanged = onValueChanged
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "",
        )
    }
}

@Composable
private fun VerticalRuler(
    steps: Int,
    centerOfScreen: Double,
    contentColor: Color,
    onValueChanged: (Float) -> Unit,
) {
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {

        val width = 100.dp
        val stepHeight = 20.dp
        val canvasHeight = stepHeight * steps + ( Dp(centerOfScreen.toFloat()) / 2 )

        Canvas(
            modifier = Modifier
                .width(width)
                .height(canvasHeight)
        ) {

            val canvasWidth = size.width

            for (i in 0 until steps + 1) {
                val yPosition = i * stepHeight.toPx() + centerOfScreen.toFloat()
                val xStart = (canvasWidth / 2)
                var xEndUp = (xStart * .85f)
                var xEndDown = (xStart * 1.15f)

                if (i % 5 == 0) {
                    xEndUp = (xStart * .75f)
                    xEndDown = (xStart * 1.25f)
                }

                drawLine(
                    color = contentColor,
                    start = Offset(xStart, yPosition),
                    end = Offset(xEndUp, yPosition),
                    strokeWidth = 3f
                )

                drawLine(
                    color = contentColor,
                    start = Offset(xStart, yPosition),
                    end = Offset(xEndDown, yPosition),
                    strokeWidth = 3f
                )


                val measurement = i + 20
                if (measurement % 5 == 0) {
                    drawContext.canvas.nativeCanvas.drawText(
                        i.toString(),
                        (xStart * .50f),
                        yPosition+15,
                        Paint().apply {
                            color = contentColor.toArgb()
                            textSize = 40f // Size of the label
                            textAlign = Paint.Align.CENTER
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        }
                    )
                }
            }
        }

        val density = LocalDensity.current
        LaunchedEffect(scrollState.value) {
            val measurement = (scrollState.value).div(density.density).div(stepHeight.value)
            onValueChanged(measurement)
        }
    }
}

@Composable
fun HorizontalRuler(
    steps: Int,
    modifier: Modifier = Modifier,
    onValueChanged: (Float) -> Unit
) {
    val contentColor: Color = if(isSystemInDarkTheme()) OffWhite else OffBlack
    BoxWithConstraints(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val centerOfScreen = constraints.maxWidth / 2.0

        HorizontalRuler(
            steps = steps,
            centerOfScreen = centerOfScreen,
            contentColor = contentColor,
            onValueChanged = onValueChanged
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "",
            tint = contentColor
        )
    }
}

@Composable
private fun HorizontalRuler(
    steps: Int,
    centerOfScreen: Double,
    contentColor: Color,
    onValueChanged: (Float) -> Unit,
) {
    val scrollState = rememberScrollState()

    val stepWidth = 20.dp
    val height = 100.dp
    val canvasWidth = stepWidth * steps

    BoxWithConstraints(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .height(height)
            .horizontalScroll(scrollState)
    ) {
        Canvas(
            modifier = Modifier
                .height(height)
                .width(canvasWidth)

        ) {

            val canvasHeight = size.height


            for (i in 0 until steps) {
                val xPosition = stepWidth.times(i).toPx() + centerOfScreen.toFloat()

                val yStart = (canvasHeight / 2)
                var yEndUp = (yStart * .85f)
                var yEndDown = (yStart * 1.15f)


                if (i % 5 == 0) {
                    yEndUp = (yStart * .75f)
                    yEndDown = (yStart * 1.25f)
                }

                drawLine(
                    color = contentColor,
                    start = Offset(xPosition, yStart),
                    end = Offset(xPosition, yEndUp),
                    strokeWidth = 3f
                )

                drawLine(
                    color = contentColor,
                    start = Offset(xPosition, yStart),
                    end = Offset(xPosition, yEndDown),
                    strokeWidth = 3f
                )

                if (i % 5 == 0) {
                    drawContext.canvas.nativeCanvas.drawText(
                        i.toString(),
                        xPosition,
                        (yStart * .50f), // Adjust label position
                        Paint().apply {
                            color = contentColor.toArgb()
                            textSize = 40f // Size of the label
                            textAlign = Paint.Align.CENTER
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        }
                    )
                }
            }
        }

        val density = LocalDensity.current
        LaunchedEffect(scrollState.value) {
            val measurement = (scrollState.value).div(density.density).div(stepWidth.value)
            onValueChanged(measurement)
        }
    }
}
