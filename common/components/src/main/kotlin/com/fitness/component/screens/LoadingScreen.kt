package com.fitness.component.screens

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max

@Preview
@Composable
fun LoadingScreen() = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()
) {
    val color: Color = ProgressIndicatorDefaults.circularColor
    val strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth
    val rotationsPerCycle = 5
    val rotationDuration = 1332
    val baseRotationAngle = 286f
    val jumpRotationAngle = 290f
    val startAngleOffset = -90f

    val headAndTailAnimationDuration = (rotationDuration * 0.5).toInt()

    val circularEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
    val circularIndicatorDiameter = 48.dp - 4.0.dp * 2

    val rotationAngleOffset = (baseRotationAngle + jumpRotationAngle) % 360f

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    val transition = rememberInfiniteTransition(label = "")
    // The current rotation around the circle, so we know where to start the rotation from
    val currentRotation = transition.animateValue(
        0,
        rotationsPerCycle,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = rotationDuration * rotationsPerCycle,
                easing = LinearEasing
            )
        ), label = ""
    )
    // How far forward (degrees) the base point should be from the start point
    val baseRotation = transition.animateFloat(
        0f,
        baseRotationAngle,
        infiniteRepeatable(
            animation = tween(
                durationMillis = rotationDuration,
                easing = LinearEasing
            )
        ), label = ""
    )
    // How far forward (degrees) both the head and tail should be from the base point
    val endAngle = transition.animateFloat(
        0f,
        jumpRotationAngle,
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = headAndTailAnimationDuration + headAndTailAnimationDuration
                0f at 0 with circularEasing
                jumpRotationAngle at headAndTailAnimationDuration
            }
        ), label = ""
    )
    val startAngle = transition.animateFloat(
        0f,
        jumpRotationAngle,
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = headAndTailAnimationDuration + headAndTailAnimationDuration
                0f at headAndTailAnimationDuration with circularEasing
                jumpRotationAngle at durationMillis
            }
        ), label = ""
    )
    Canvas(Modifier.progressSemantics().size(circularIndicatorDiameter)) {
        val currentRotationAngleOffset = (currentRotation.value * rotationAngleOffset) % 360f

        // How long a line to draw using the start angle as a reference point
        val sweep = abs(endAngle.value - startAngle.value)

        // Offset by the constant offset and the per rotation offset
        val offset = startAngleOffset + currentRotationAngleOffset + baseRotation.value
        drawIndeterminateCircularIndicator(
            startAngle.value + offset,
            strokeWidth,
            sweep,
            color,
            stroke
        )
    }
}

private fun DrawScope.drawIndeterminateCircularIndicator(
    startAngle: Float,
    strokeWidth: Dp,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {

    val circularIndicatorDiameter = 48.0.dp - 4.0.dp * 2
    val squareStrokeCapOffset = (180.0 / PI).toFloat() * (strokeWidth / (circularIndicatorDiameter / 2)) / 2f
    val adjustedStartAngle = startAngle + squareStrokeCapOffset
    val adjustedSweep = max(sweep, 0.1f)

    drawCircularIndicator(adjustedStartAngle, adjustedSweep, color, stroke)
}

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}