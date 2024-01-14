package com.fitness.component.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.ItemState
import com.fitness.component.properties.STANDARD_TEXT_SIZE_SMALL
import com.fitness.component.selectPlate
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.DeepOrange
import com.fitness.theme.ui.Saffron
import com.fitness.theme.ui.primaryVariantHub
import com.skydoves.landscapist.coil.CoilImage
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
fun NutritionItemWithImage(
    title: String = "Kreplach",
    imageModel: String = "",
    itemState: ItemState = ItemState.UNSELECTED,
    time: Double? = 0.0,
    energy: Double? = 0.0,
    fat: Double? = 0.0,
    net: Double? = 0.0,
    onClickItem: () -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickShare: () -> Unit = {}
) = BodyBalanceTheme {
    Surface {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val (titleRef, imageRef, contentRef, shareRef, addRef) = createRefs()
            val guideTop = createGuidelineFromTop(.20f)
            val guideEnd = createGuidelineFromEnd(.05f)
            val guideStart = createGuidelineFromStart(.05f)
            val guideBottom = createGuidelineFromBottom(.20f)
            val guideAddButtonTop = createGuidelineFromBottom(.40f)

            Text(text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.constrainAs(titleRef) {
                    start.linkTo(imageRef.end, 5.dp)
                    top.linkTo(parent.top, 5.dp)
                    end.linkTo(contentRef.end, 5.dp)
                    bottom.linkTo(contentRef.top, 5.dp)
                    width = Dimension.fillToConstraints
                }
            )

            ItemContent(
                energy = energy,
                fat = fat,
                net = net,
                modifier = Modifier.constrainAs(contentRef) {
                    start.linkTo(guideStart)
                    end.linkTo(guideEnd)
                    top.linkTo(guideTop)
                    bottom.linkTo(guideBottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            )

            ItemImage(
                imageModel = imageModel,
                modifier = Modifier.constrainAs(imageRef) {
                    top.linkTo(parent.top, 5.dp)
                    start.linkTo(parent.start, 5.dp)
                }
            )

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = CircleShape,
                modifier = Modifier
                    .clickable { Log.e("NutritionItemWithImage", "share") }
                    .constrainAs(shareRef) {
                        start.linkTo(contentRef.start, 15.dp)
                        top.linkTo(guideAddButtonTop, 5.dp)
                        bottom.linkTo(parent.bottom, 5.dp)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_share),
                    contentDescription = stringResource(id = R.string.content_description_share),
                    tint = primaryVariantHub,
                    modifier = Modifier.padding(5.dp)
                )
            }


            val animatable = remember { Animatable(0f) }


            LaunchedEffect(key1 = itemState) {
                when (itemState) {
                    ItemState.SELECTED -> {
                        animatable.animateTo(
                            targetValue = 720f,
                            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                        )
                    }

                    else -> {
                        animatable.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                        )
                    }
                }
            }

            if (animatable.value < 270f) {
                AddToPlannerButton(modifier = Modifier
                    .clickable { onClickAdd() }
                    .graphicsLayer { rotationZ = animatable.value }
                    .constrainAs(addRef) {
                        end.linkTo(contentRef.end, 10.dp)
                        top.linkTo(guideAddButtonTop, 5.dp)
                        bottom.linkTo(parent.bottom, 5.dp)
                    }
                )
            } else if (animatable.value > 270f && animatable.value < 540f) {
                TransitionButton(modifier = Modifier
                    .graphicsLayer { rotationZ = animatable.value }
                    .constrainAs(addRef) {
                        end.linkTo(contentRef.end, 10.dp)
                        top.linkTo(guideAddButtonTop, 5.dp)
                        bottom.linkTo(parent.bottom, 5.dp)
                    }
                )
            } else if (animatable.value > 540f) {
                CheckmarkConfirmButton(modifier = Modifier
                    .clickable { onClickAdd() }
                    .graphicsLayer { rotationZ = animatable.value }
                    .constrainAs(addRef) {
                        end.linkTo(contentRef.end, 10.dp)
                        top.linkTo(guideAddButtonTop, 5.dp)
                        bottom.linkTo(parent.bottom, 5.dp)
                    }
                )
            }

        }


    }
}

@Composable
private fun ItemContent(
    modifier: Modifier = Modifier,
    energy: Double? = 0.0,
    fat: Double? = 0.0,
    net: Double? = 0.0
) {
    Card(modifier = modifier, shape = RoundedCornerShape(5), colors = CardDefaults.cardColors()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            val (
                title,
                energyIcon,
                energyValue,
                fatIcon,
                fatValue,
                carbsIcon,
                carbsValue,
            ) = createRefs()

            val midGuide = createGuidelineFromTop(.45f)
            val startGuide = createGuidelineFromStart(.40f)

            Text(
                text = stringResource(id = R.string.title_nutritional_summary),
                fontSize = STANDARD_TEXT_SIZE_SMALL,
                fontWeight = FontWeight.Thin,
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(startGuide)
                    bottom.linkTo(midGuide, 8.dp)
                }
            )

            energy?.let {
                Icon(
                    painter = painterResource(id = R.drawable.icon_calories),
                    contentDescription = stringResource(id = R.string.content_description_icon_energy),
                    tint = DeepOrange,
                    modifier = Modifier.constrainAs(energyIcon) {
                        start.linkTo(startGuide)
                        top.linkTo(midGuide)
                    }
                )

                Text(
                    text = "${energy.toInt()}",
                    fontSize = STANDARD_TEXT_SIZE_SMALL,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.constrainAs(energyValue) {
                        start.linkTo(energyIcon.end, 5.dp)
                        top.linkTo(energyIcon.top)
                        bottom.linkTo(energyIcon.bottom)
                    }
                )
            }

            fat?.let {
                Icon(
                    painter = painterResource(id = R.drawable.icon_fat),
                    contentDescription = stringResource(id = R.string.content_description_icon_fat),
                    tint = primaryVariantHub,
                    modifier = Modifier.constrainAs(fatIcon) {
                        top.linkTo(midGuide)
                        start.linkTo(energyValue.end, 30.dp)
                    }
                )

                Text(
                    text = "${fat.toInt()}",
                    fontSize = STANDARD_TEXT_SIZE_SMALL,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.constrainAs(fatValue) {
                        start.linkTo(fatIcon.end, 5.dp)
                        top.linkTo(fatIcon.top)
                        bottom.linkTo(fatIcon.bottom)
                    }
                )
            }

            net?.let {
                Icon(
                    painter = painterResource(id = R.drawable.icon_carbs),
                    contentDescription = stringResource(id = R.string.content_description_icon_net_carbs),
                    tint = Saffron,
                    modifier = Modifier.constrainAs(carbsIcon) {
                        top.linkTo(midGuide)
                        start.linkTo(fatValue.end, 30.dp)
                    }
                )

                Text(
                    text = "${net.toInt()}",
                    fontSize = STANDARD_TEXT_SIZE_SMALL,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.constrainAs(carbsValue) {
                        top.linkTo(carbsIcon.top)
                        start.linkTo(carbsIcon.end, 5.dp)
                        bottom.linkTo(carbsIcon.bottom)
                    }
                )
            }
        }
    }
}

@Composable
private fun ItemImage(modifier: Modifier = Modifier, imageModel: String = "") {
    val plate by remember { mutableIntStateOf(selectPlate()) }
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = CircleShape, modifier = modifier.size(135.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = plate),
                contentDescription = stringResource(id = R.string.content_description_icon_plate),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
            )

            var isVisible by remember { mutableStateOf(false) }

            Card(
                shape = CircleShape,
                modifier = if (isVisible) Modifier.size(90.dp) else Modifier
            ) {
                CoilImage(
                    imageModel = { imageModel },
                    success = { _, painter ->
                        isVisible = true
                        Image(
                            painter = painter,
                            contentDescription = "Loaded image",
                            modifier = Modifier.fillMaxSize() // Adjust modifier as needed
                        )
                    },
                    failure = {
                        isVisible = false
                    }
                )
            }
        }
    }
}