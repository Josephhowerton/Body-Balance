package com.fitness.component.components

import android.util.Log.e
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import extensions.Dark
import extensions.Light
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.fitness.component.calculateWeightProgressAsFraction
import com.fitness.component.formatWeightProgress
import com.fitness.component.isOnTrack
import com.fitness.resources.R
import com.fitness.theme.ui.Red

@Light
@Dark
@Composable
private fun CircleProgressPreview() {
    BodyBalanceTheme {
        Surface {
            CircleProgressWithTextComponent()
        }
    }
}

@Light
@Dark
@Composable
private fun WeightProgressPreview() {
    BodyBalanceTheme {
        Surface {
            WeightProgressComponent()
        }
    }
}

@Light
@Dark
@Composable
private fun LineProgressPreview() {
    BodyBalanceTheme {
        Surface {
            LineProgressComponent()
        }
    }
}


@Composable
fun CircleProgressComponent(
    modifier: Modifier = Modifier,
    isTimer: Boolean = false,
    progress: Double = .25,
    progressColor: Color = Green,
    canvasSize: Int = 50,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {

    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val sweepAngleFormula = if (isTimer) (360 - (360f * progress)) else (360 * progress)
        val animateFloat by remember { mutableStateOf(Animatable((360 * progress).toFloat())) }

        LaunchedEffect(animateFloat) {
            animateFloat.animateTo(
                targetValue = sweepAngleFormula.toFloat(),
                animationSpec = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
            )
        }

        Canvas(modifier = Modifier.size(canvasSize.dp)) {
            drawArc(
                color = if (isDarkTheme) White else Black,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 2f, cap = StrokeCap.Round)
            )


            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = animateFloat.value,
                useCenter = false,
                style = Stroke(width = 10f, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
fun CircleProgressWithTextComponent(
    modifier: Modifier = Modifier,
    title: String = "Carb",
    currentValue: Double = 90.0,
    targetValue: Double = 100.0
) {

    val progress by remember { mutableDoubleStateOf(currentValue.div(targetValue)) }

    e("Progress", progress.toString())

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.wrapContentSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
            CircleProgressComponent(
                isTimer = false,
                progress = progress,
                progressColor = if(progress <= 1.0) Green else Red,
                canvasSize = 200,
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = title,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(150.dp)
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun WeightProgressComponent(
    modifier: Modifier = Modifier,
    startedWeight: Double = 298.0,
    currentWeight: Double = 288.0,
    targetWeight: Double = 290.0
) {

    val weightProgress = calculateWeightProgressAsFraction(startedWeight, currentWeight, targetWeight)

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.wrapContentSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
            CircleProgressComponent(
                isTimer = false,
                progress = weightProgress,
                progressColor = if (isOnTrack(
                        startedWeight,
                        currentWeight,
                        targetWeight
                    )
                ) Green else Color.Red,
                canvasSize = 200,
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "${
                    formatWeightProgress(
                        startedWeight,
                        currentWeight,
                        targetWeight
                    )
                } target weight",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .width(150.dp)
            )
        }

        Spacer(modifier = modifier.size(15.dp))

        Text(
            text = stringResource(id = R.string.target_weight_desc, targetWeight),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LineProgressComponent(
    modifier: Modifier = Modifier,
    progress: Double = .45,
    progressBarColor: Color = Green,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(5.dp)
    ) {

        val animateFloat by remember { mutableStateOf(Animatable(0f)) }
        LaunchedEffect(animateFloat) {
            animateFloat.animateTo(
                targetValue = progress.toFloat(),
                animationSpec = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
            )
        }

        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(
                start = Offset(x = 0f, y = 1f),
                end = Offset(x = size.width, y = 1f),
                color = if (isDarkTheme) White else Black,
                strokeWidth = 4f,
                alpha = .5f,
                cap = StrokeCap.Round,

                )

            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = (size.width * animateFloat.value), y = 0f),
                color = progressBarColor,
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
    }
}