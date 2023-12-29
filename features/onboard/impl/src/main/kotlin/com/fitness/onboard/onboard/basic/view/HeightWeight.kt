package com.fitness.onboard.onboard.basic.view

import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme

@Preview(showBackground = true)
@Composable
fun MeasurementPreview() = Surface {
    Measurement()
}

@Preview(showBackground = true)
@Composable
private fun HeightWeightPreview() = BodyBalanceTheme {
    Surface {
        HeightWeight(state = BasicInformationState())
    }
}

@Composable
fun HeightWeight(
    state: BasicInformationState,
    onTriggerEvent: (BasicInformationEvent) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, image, weightMeasurement, heightMeasurement, continueButton) = createRefs()

        val topGuide = createGuidelineFromTop(50.dp)
        val midGuide = createGuidelineFromTop(.5f)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

        StandardTitleText(text = R.string.title_continue, modifier = Modifier.constrainAs(title){
            top.linkTo(topGuide)
            start.linkTo(startGuide)
        })

        Image(
            painter = painterResource(id = R.drawable.icon_women_temp),
            contentDescription = "women",
            modifier = Modifier.constrainAs(image){
                top.linkTo(title.bottom, 50.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
            }
        )

        Measurement(Modifier.constrainAs(weightMeasurement){
            bottom.linkTo(parent.bottom)
            start.linkTo(startGuide)
        })

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

@Composable
fun Measurement(modifier: Modifier = Modifier, onMeasurementChanged: (Float) -> Unit = {}) =
    Box(contentAlignment = Alignment.BottomCenter, modifier = modifier) {
    CorrectedRuler(onMeasurementChanged)
    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "", modifier = Modifier.padding(bottom = 15.dp))
}

@Composable
fun CorrectedRuler(
    currentNumber: (Float) -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 100.dp,
    steps: Int = 700,  // Number of steps in the ruler
    stepWidth: Dp = 20.dp  // Width of each step
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current

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
                val yEndUp = (yStart * .85f)
                val yEndDown = (yStart * 1.15f)

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

                if (i % 5 == 0) {
                    drawContext.canvas.nativeCanvas.drawText(
                        (i + 80).toString(),
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