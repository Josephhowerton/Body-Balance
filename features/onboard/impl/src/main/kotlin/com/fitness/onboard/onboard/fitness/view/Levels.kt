package com.fitness.onboard.onboard.fitness.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.properties.GuidelineProperties.TOP
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.EPhysicalActivityLevel
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun PreviewFitnessLevels() = BodyBalanceTheme {
    Surface {
        FitnessLevels()
    }
}


@Composable
fun FitnessLevels(onTriggerEvent: (FitnessEvent) -> Unit = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title,
            desc,
            avatar,
            levels,
            levelDescription,
            continueButton
        ) = createRefs()

        val topGuide = createGuidelineFromTop(TOP)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

        var currentLevel by remember { mutableStateOf(EPhysicalActivityLevel.SEDENTARY) }

        StandardTitleText(
            text = R.string.title_activity_level,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(startGuide)
                top.linkTo(topGuide)
            }
        )

        StandardTextSmall(
            text = R.string.desc_current_activity_level,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(desc) {
                start.linkTo(startGuide)
                top.linkTo(title.bottom, 10.dp)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.icon_fruit),
            contentDescription = "women",
            modifier = Modifier.constrainAs(avatar) {
                top.linkTo(desc.bottom)
                bottom.linkTo(bottomGuide)
                start.linkTo(startGuide)
                end.linkTo(levels.start)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(levels) {
                    end.linkTo(parent.end)
                    top.linkTo(desc.bottom)
                    bottom.linkTo(continueButton.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            EPhysicalActivityLevel.values().forEach { cardLevel ->
                PhysicalActivityCard(
                    size = 90.dp,
                    level = cardLevel,
                    currentLevel = currentLevel,
                    onClick = { currentLevel = it }
                )
            }
        }

        Text(
            text = stringResource(id = currentLevel.description),
            modifier = Modifier.constrainAs(levelDescription) {
                start.linkTo(startGuide)
                end.linkTo(continueButton.start)
                top.linkTo(continueButton.top)
                bottom.linkTo(continueButton.bottom)
                width = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = { onTriggerEvent(FitnessEvent.FitnessLevel(level = currentLevel)) },
            modifier = Modifier.padding(15.dp).constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            })
        {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

@Composable
private fun PhysicalActivityCard(
    size: Dp,
    level: EPhysicalActivityLevel,
    currentLevel: EPhysicalActivityLevel,
    onClick: (EPhysicalActivityLevel) -> Unit
) {
    val cardLevel by remember { mutableStateOf(level) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor =
            if (cardLevel == currentLevel)
                Green
            else
                MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = CircleShape,
        modifier = Modifier
            .size(size)
            .clickable {
                if (cardLevel != currentLevel) {
                    onClick(cardLevel)
                }
            }
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(size)
        ) {
            StandardTextSmall(text = level.title)
        }
    }

}