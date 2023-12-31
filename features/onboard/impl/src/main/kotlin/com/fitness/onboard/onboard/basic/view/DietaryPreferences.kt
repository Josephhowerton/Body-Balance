package com.fitness.onboard.onboard.basic.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.components.TextItemComponent
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationState
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun DietaryPreferencesPreview() = BodyBalanceTheme {
    Surface {
        DietaryPreferences(BasicInformationState())
    }
}

@Composable
fun DietaryPreferences(
    state: BasicInformationState,
    onTriggerEvent: (BasicInformationEvent) -> Unit = {}
) = ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    val (title, list, continueButton) = createRefs()

    val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
    val endGuide = createGuidelineFromEnd(GuidelineProperties.END)


    val gridState = rememberLazyGridState()
    val isScrolling = gridState.isScrollInProgress

    LazyVerticalGrid(
        state = gridState,
        modifier = Modifier
            .constrainAs(list) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(title.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
        columns = GridCells.Fixed(3)
    ) {
        items(40) {
            TextItemComponent("Dietary Restrictions", onClick = { Log.e("DietaryPreferences", it) })
        }
    }

    AnimatedVisibility(
        visible = !isScrolling,
        modifier = Modifier.constrainAs(title) {
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
                .constrainAs(title) {
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
                    text = R.string.title_dietary_preferences,
                    modifier = Modifier.padding()
                )

                Spacer(modifier = Modifier.size(10.dp))

                StandardTextSmall(
                    text = R.string.desc_dietary_preferences,
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
            .constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape,
                )
                .constrainAs(continueButton) {
                    end.linkTo(endGuide)
                    bottom.linkTo(bottomGuide)
                }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}