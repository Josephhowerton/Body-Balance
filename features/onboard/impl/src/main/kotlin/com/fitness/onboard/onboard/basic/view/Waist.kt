package com.fitness.onboard.onboard.basic.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.HorizontalRuler
import com.fitness.component.HorizontalRulerCorrected
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import com.fitness.resources.R

@Light
@Dark
@Composable
private fun PreviewWaistSize() = BodyBalanceTheme {
    Surface {
        WaistMeasurement()
    }
}

@Composable
fun WaistMeasurement(onTriggerEvent: (BasicInformationEvent) -> Unit = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (titleRef, descriptionRef, waistSizeRef, horizontalRulerRef, measurementIconRef, continueRef) = createRefs()

        val topGuideline = createGuidelineFromTop(.1f)
        val startGuideline = createGuidelineFromStart(.05f)
        val endGuideline = createGuidelineFromEnd(.05f)

        var waistSize by remember { mutableDoubleStateOf(30.0) }

        StandardTitleText(
            text = R.string.title_waist_size,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextSmall(
            text = R.string.description_waist_size,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(descriptionRef) {
                top.linkTo(titleRef.bottom, 20.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = waistSize.toString(),
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(waistSizeRef) {
                top.linkTo(descriptionRef.bottom, 20.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                bottom.linkTo(continueRef.top)
            }
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "",
            modifier = Modifier.padding(bottom = 15.dp).constrainAs(measurementIconRef){
                start.linkTo(horizontalRulerRef.start)
                end.linkTo(horizontalRulerRef.end)
                bottom.linkTo(parent.bottom)
            }
        )

        HorizontalRulerCorrected(
            currentNumber = { waistSize = it },
            modifier = Modifier.constrainAs(horizontalRulerRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )


        Button(
            onClick = { onTriggerEvent(BasicInformationEvent.Waist(waistSize)) },
            modifier = Modifier.constrainAs(continueRef) {
                end.linkTo(endGuideline)
                bottom.linkTo(horizontalRulerRef.top, 20.dp)
            }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}