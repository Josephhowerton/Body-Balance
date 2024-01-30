package com.fitness.onboard.onboard.basic.view


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.HorizontalRuler
import com.fitness.component.VerticalRuler
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.ELengthUnit
import enums.EMassUnit
import enums.formatHeight
import enums.formatWeight
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
private fun WeightPreview() = BodyBalanceTheme {
    Surface {
        WeightMeasurement()
    }
}

@Preview(showBackground = true)
@Composable
private fun HeightPreview() = BodyBalanceTheme {
    Surface {
        HeightMeasurement()
    }
}

@Composable
fun WeightMeasurement(onTriggerEvent: (BasicInformationEvent) -> Unit = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, desc, image, weightMeasurement, weightValue, continueButton, bottomSheet) = createRefs()

        val topGuide = createGuidelineFromTop(50.dp)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)
        val bottomGuide = createGuidelineFromBottom(10.dp)

        var weightNumber by remember { mutableDoubleStateOf(0.0) }
        var units by remember { mutableStateOf(EMassUnit.POUNDS) }

        StandardTitleText(text = R.string.select_weight,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            }
        )

        StandardTextSmall(
            text = R.string.desc_select_weight,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(desc) {
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.icon_women_temp),
            contentDescription = "women",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(desc.bottom, 75.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide, 75.dp)
            }
        )

        Text(text = formatWeight(weightNumber, units),
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(weightValue) {
                top.linkTo(image.bottom)
                bottom.linkTo(continueButton.top)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
            })

        Button(
            onClick = { onTriggerEvent(BasicInformationEvent.Weight(weight = weightNumber.toDouble())) },
            modifier = Modifier.constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(weightMeasurement.top, 20.dp)
            })
        {
            Text(text = stringResource(id = R.string.title_continue))
        }

        WeightMeasurement(
            onMeasurementChanged = { weightNumber = it },
            modifier = Modifier.constrainAs(weightMeasurement) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomGuide)
            }
        )
    }
}

@Composable
fun HeightMeasurement(onTriggerEvent: (BasicInformationEvent) -> Unit = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, desc, image, weightLocked, heightMeasurement, heightValue, continueButton, bottomSheet) = createRefs()

        val topGuide = createGuidelineFromTop(50.dp)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

        var heightNumber by remember { mutableDoubleStateOf(0.0) }
        var units by remember { mutableStateOf(ELengthUnit.FEET) }

        StandardTitleText(text = R.string.select_height,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(topGuide)
                end.linkTo(endGuide)
            }
        )

        StandardTextSmall(
            text = R.string.desc_select_height,
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(desc) {
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(heightMeasurement.end)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        val isVisible = remember { mutableStateOf(false) }
        val rotationYAnimatable = remember { Animatable(0f) }

        val textColor by animateColorAsState(
            targetValue = if (isVisible.value) Green else Color.Black,
            animationSpec = tween(durationMillis = 2000),
            label = "textColor"
        )

        LaunchedEffect(key1 = Unit) {
            delay(250)
            isVisible.value = true
            rotationYAnimatable.animateTo(
                targetValue = 720f, // Rotate 360 degrees
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
        }

        AnimatedVisibility(
            visible = isVisible.value,
            modifier = Modifier.constrainAs(weightLocked) {
                top.linkTo(desc.bottom, 10.dp)
                end.linkTo(endGuide)
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(imageVector = Icons.Default.Check,
                    tint = textColor,
                    contentDescription = stringResource(id = R.string.content_description_checkmark),
                    modifier = Modifier.graphicsLayer {
                        rotationY = rotationYAnimatable.value
                        cameraDistance = 12f * density
                    }
                )

                Spacer(modifier = Modifier.size(5.dp))

                StandardTextSmall(
                    text = R.string.weight,
                    color = textColor
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.icon_women_temp),
            contentDescription = "women",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(desc.bottom, 75.dp)
                start.linkTo(startGuide, 75.dp)
                end.linkTo(endGuide)
            }
        )

        Text(text = formatHeight(heightNumber, units),
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(heightValue) {
                top.linkTo(image.bottom)
                bottom.linkTo(continueButton.top)
                start.linkTo(startGuide, 75.dp)
                end.linkTo(endGuide)
            })

        Button(
            onClick = { onTriggerEvent(BasicInformationEvent.Height(height = heightNumber)) },
            modifier = Modifier.constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }

        HeightMeasurement(
            onMeasurementChanged = { heightNumber = it },
            modifier = Modifier.constrainAs(heightMeasurement) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
private fun WeightMeasurement(modifier: Modifier = Modifier, onMeasurementChanged: (Double) -> Unit = {}) =
    Box(contentAlignment = Alignment.BottomCenter, modifier = modifier) {
        HorizontalRuler(onMeasurementChanged)
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "",
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }


@Composable
private fun HeightMeasurement(modifier: Modifier = Modifier, onMeasurementChanged: (Double) -> Unit = {}) =
    Box(contentAlignment = Alignment.CenterEnd, modifier = modifier) {
        VerticalRuler(onMeasurementChanged)
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }
