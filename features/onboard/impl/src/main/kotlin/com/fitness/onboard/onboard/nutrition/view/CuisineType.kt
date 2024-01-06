package com.fitness.onboard.onboard.nutrition.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.components.TextItemTransition
import com.fitness.component.components.TextItemSelected
import com.fitness.component.components.TextItemUnSelected
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.nutrition.viewmodel.NutritionEvent
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.ECuisineType
import extensions.Dark
import extensions.Light


@Light
@Dark
@Composable
private fun CuisineTypePreview() = BodyBalanceTheme {
    Surface {
        CuisineType()
    }
}

@Composable
fun CuisineType(onTriggerEvent: (NutritionEvent) -> Unit = {}) = ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    val (title, list, continueButton) = createRefs()

    val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
    val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

    val gridState = rememberLazyGridState()
    val isScrolling = gridState.isScrollInProgress

    val cuisineType = remember { mutableListOf<ECuisineType>() }

    LazyVerticalGrid(
        state = gridState,
        modifier = Modifier.constrainAs(list) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(title.bottom)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        },
        columns = GridCells.Fixed(3)
    ) {
        items(ECuisineType.values()) {
            CuisineTypeItem(it, onClick = {
                if (cuisineType.contains(it)) {
                    cuisineType.remove(it)
                } else {
                    cuisineType.add(it)
                }
            })
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
                    text = R.string.onboarding_cuisine_types_title,
                    modifier = Modifier.padding()
                )

                Spacer(modifier = Modifier.size(10.dp))

                StandardTextSmall(
                    text = R.string.onboarding_cuisine_types_description,
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
            onClick = { onTriggerEvent(NutritionEvent.CuisineType(cuisineType = cuisineType)) },
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

@Composable
fun CuisineTypeItem(
    label: ECuisineType,
    onClick:() -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp
) {

    var isClicked by remember { mutableStateOf(false) }

    val rotationAnimatable = remember { Animatable(0f) }

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            rotationAnimatable.animateTo(
                targetValue = 360f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        } else {
            rotationAnimatable.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable {
                isClicked = !isClicked
                onClick()
            }
            .wrapContentSize()
            .graphicsLayer {
                rotationY = rotationAnimatable.value
            }

    ) {
        if (rotationAnimatable.value < 90f) {
            TextItemUnSelected(text = stringResource(id = label.title), size = size)
        } else if (rotationAnimatable.value > 270f) {
            TextItemSelected(text = stringResource(id = label.title), size = size)
        }
    }

    if(rotationAnimatable.value > 20f && rotationAnimatable.value < 270f){
        TextItemTransition(size = size)
    }
}