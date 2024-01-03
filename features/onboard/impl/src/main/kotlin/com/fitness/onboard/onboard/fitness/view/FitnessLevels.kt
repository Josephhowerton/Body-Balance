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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.fitness.viewmodel.FitnessEvent
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.FitnessLevel
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
fun FitnessLevels(onTriggerEvent: (FitnessEvent) -> Unit = {}){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title,
            desc,
            avatar,
            levels,
            continueButton
        ) = createRefs()

        val topGuide = createGuidelineFromTop(GuidelineProperties.TOP)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

        var level by remember { mutableStateOf(FitnessLevel.Beginner) }

        StandardTitleText(
            text = R.string.title_select_fitness_level,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(startGuide)
                top.linkTo(topGuide)
            }
        )

        StandardTextSmall(
            text = R.string.desc_select_fitness_level,
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

        FitnessLevelOptions(
            onSelection = { level = it },
            modifier = Modifier.constrainAs(levels) {
                end.linkTo(parent.end)
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
                height = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = { onTriggerEvent(FitnessEvent.FitnessLevel(level = level)) },
            modifier = Modifier.constrainAs(continueButton) {
                end.linkTo(endGuide)
                top.linkTo(bottomGuide)
            })
        {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

@Preview
@Composable
private fun FitnessLevelOptions(
    modifier: Modifier = Modifier,
    onSelection: (FitnessLevel) -> Unit = {}
) {
    val size = 96.dp
    var level by remember { mutableStateOf(FitnessLevel.Beginner) }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.padding(16.dp)
    ) {

        Card(
            colors = CardDefaults.cardColors(containerColor = if (level == FitnessLevel.Beginner) Green else MaterialTheme.colorScheme.surfaceVariant),
            shape = CircleShape,
            modifier = Modifier
                .size(size)
                .clickable {
                    if (level != FitnessLevel.Beginner) {
                        onSelection(FitnessLevel.Beginner)
                        level = FitnessLevel.Beginner
                    }
                }
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.size(size)
            ){
                StandardTextSmall(text = R.string.title_beginner)
            }

        }

        Card(
            colors = CardDefaults.cardColors(containerColor = if (level == FitnessLevel.Intermediate) Green else MaterialTheme.colorScheme.surfaceVariant),
            shape = CircleShape,
            modifier = Modifier
                .size(size)
                .clickable {
                    if (level != FitnessLevel.Intermediate) {
                        onSelection(FitnessLevel.Intermediate)
                        level = FitnessLevel.Intermediate
                    }
                }
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.size(size)
            ){
                StandardTextSmall(text = R.string.title_intermediate)
            }

        }

        Card(
            colors = CardDefaults.cardColors(containerColor = if (level == FitnessLevel.Advance) Green else MaterialTheme.colorScheme.surfaceVariant),
            shape = CircleShape,
            modifier = Modifier
                .size(size)
                .clickable {
                    if (level != FitnessLevel.Advance) {
                        onSelection(FitnessLevel.Advance)
                        level = FitnessLevel.Advance
                    }
                }
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.size(size)
            ){
                StandardTextSmall(text = R.string.title_advance)
            }

        }
    }


}