package com.fitness.component

import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalRuler(
    currentNumber: (Float) -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 100.dp,
    steps: Int = 700,  // Number of steps in the ruler
    stepWidth: Dp = 20.dp  // Width of each step
) {
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .height(height)
            .horizontalScroll(scrollState)
    ) {
        val canvasWidth = stepWidth * steps

        Canvas(
            modifier = Modifier
                .height(height)
                .width(canvasWidth)
        ) {
            val canvasHeight = size.height

            for (i in 0 until steps) {
                val xPosition = i * stepWidth.toPx()
                val yStart = (canvasHeight / 2)
                var yEndUp = (yStart * .85f)
                var yEndDown = (yStart * 1.15f)


                if(i % 5 == 0){
                    yEndUp = (yStart * .75f)
                    yEndDown = (yStart * 1.25f)
                }

                drawLine(
                    color = Color.Black,
                    start = Offset(xPosition, yStart),
                    end = Offset(xPosition, yEndUp),
                    strokeWidth = 3f
                )

                drawLine(
                    color = Color.Black,
                    start = Offset(xPosition, yStart),
                    end = Offset(xPosition, yEndDown),
                    strokeWidth = 3f
                )

                val measurement = i + 80
                if (measurement % 5 == 0) {
                    drawContext.canvas.nativeCanvas.drawText(
                        measurement.toString(),
                        xPosition,
                        (yStart * .50f), // Adjust label position
                        Paint().apply {
                            color = android.graphics.Color.BLACK
                            textSize = 40f // Size of the label
                            textAlign = Paint.Align.CENTER
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        }
                    )
                }
            }
        }

        LaunchedEffect(scrollState.value) {

            val centerOfScreen = (constraints.minHeight/2)
            val step = (((scrollState.value*stepWidth.value)/centerOfScreen)/8) + 90

            Log.e("CenterOfScreen", centerOfScreen.toString())

            // Update the current number based on stepAtCenter
            currentNumber(step)
        }
    }
}

@Composable
fun VerticalRuler(
    currentNumber: (Float) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    steps: Int = 275,
    stepWidth: Dp = 20.dp
) {
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .width(width)
            .verticalScroll(scrollState)
    ) {
        val canvasWidth = stepWidth * steps

        Canvas(
            modifier = Modifier
                .width(width)
                .height(canvasWidth)
        ) {
            val canvasHeight = size.width

            for (i in 0 until steps) {
                val yPosition = i * stepWidth.toPx()
                val xStart = (canvasHeight / 2)
                var xEndUp = (xStart * .85f)
                var xEndDown = (xStart * 1.15f)

                if(i % 5 == 0){
                    xEndUp = (xStart * .75f)
                    xEndDown = (xStart * 1.25f)
                }

                drawLine(
                    color = Color.Black,
                    start = Offset(xStart, yPosition),
                    end = Offset(xEndUp, yPosition),
                    strokeWidth = 3f
                )

                drawLine(
                    color = Color.Black,
                    start = Offset(xStart, yPosition),
                    end = Offset(xEndDown, yPosition),
                    strokeWidth = 3f
                )

                val measurement = i + 20
                if (measurement % 5 == 0) {
                    drawContext.canvas.nativeCanvas.drawText(
                        i.toString(),
                        (xStart * .50f),
                        yPosition, // Adjust label position
                        Paint().apply {
                            color = android.graphics.Color.BLACK
                            textSize = 40f // Size of the label
                            textAlign = Paint.Align.CENTER
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        }
                    )
                }
            }
        }

        LaunchedEffect(scrollState.value) {

            val centerOfScreen = (constraints.maxWidth/2)
            val step = (((scrollState.value*stepWidth.value)/centerOfScreen)/8) + 20.5f

            Log.e("CenterOfScreen", centerOfScreen.toString())

            // Update the current number based on stepAtCenter
            currentNumber(step)
        }
    }
}
