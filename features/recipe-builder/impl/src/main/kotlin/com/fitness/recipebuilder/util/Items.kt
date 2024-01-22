package com.fitness.recipebuilder.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.domain.model.nutrition.Measure
import com.fitness.resources.R
import com.fitness.theme.ui.Green
import com.skydoves.landscapist.coil.CoilImage
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
fun SquareNutritionItem(
    title: String = "Kreplach",
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) = Card(
    colors = CardDefaults.cardColors(containerColor = if(isSelected) Green else MaterialTheme.colorScheme.surfaceVariant),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier
        .size(75.dp)
        .clickable { onClick() }
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            fontSize = 8.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
}

@Light
@Dark
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmallNutritionItem(
    title: String = "Kreplach",
    quantity: Double = 3.0,
    measure: String = "Servings",
    onClickModify: () -> Unit = {},
) = Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier
        .height(50.dp)
        .width(55.dp)
        .clickable { onClickModify() }
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (titleRef, quantityRef, modifyRef) = createRefs()

        val topGuide = createGuidelineFromTop(5.dp)
        val endGuide = createGuidelineFromEnd(5.dp)
        val startGuide = createGuidelineFromStart(5.dp)

        Text(
            text = title,
            fontSize = 8.sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    bottom.linkTo(quantityRef.top)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "$quantity $measure",
            fontSize = 8.sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(quantityRef) {
                    start.linkTo(startGuide)
                    bottom.linkTo(modifyRef.top)
                    end.linkTo(endGuide)
                    top.linkTo(titleRef.bottom)
                    width = Dimension.fillToConstraints
                }
        )


        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Green),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
            onClick = { onClickModify() },
            modifier = Modifier.height(15.dp).constrainAs(modifyRef) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_edit),
                contentDescription = stringResource(id = R.string.content_description_icon_edit),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Light
@Dark
@Composable
fun NutritionItem(
    title: String = "Kreplach",
    imageModel: String? = "",
    energy: Double? = 0.0,
    fat: Double? = 0.0,
    protein: Double? = 0.0,
    net: Double? = 0.0,
    onClickItem: () -> Unit = {},
) = Card(
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier.clickable { onClickItem() }) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {

        val (imageRef, titleRef, nutrientsEnergyFat, nutrientsProteinNet) = createRefs()

        val guideTop = createGuidelineFromTop(.10f)
        val guideMid = createGuidelineFromEnd(.35f)
        val guideEnd = createGuidelineFromEnd(.05f)
        val guideBottom = createGuidelineFromBottom(.10f)


        ItemImage(
            imageModel = imageModel,
            size = 75.dp,
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(guideTop)
                    start.linkTo(parent.start)
                    bottom.linkTo(guideBottom)
                }
        )

        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(guideTop)
                end.linkTo(guideMid)
                start.linkTo(imageRef.end, 10.dp)
                bottom.linkTo(guideBottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
        )

        Row(modifier = Modifier.constrainAs(nutrientsEnergyFat) {
            top.linkTo(titleRef.top)
            end.linkTo(guideEnd)
        }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                energy?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_calories),
                        contentDescription = stringResource(id = R.string.content_description_icon_energy)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it.toInt().toString())
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                fat?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_fat),
                        contentDescription = stringResource(id = R.string.content_description_icon_fat)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it.toInt().toString())
                }
            }
        }

        Row(modifier = Modifier.constrainAs(nutrientsProteinNet) {
            bottom.linkTo(guideBottom)
            end.linkTo(guideEnd)
        }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                protein?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_fat),
                        contentDescription = stringResource(id = R.string.content_description_icon_protein)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it.toInt().toString())
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                net?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_carbs),
                        contentDescription = stringResource(id = R.string.content_description_icon_net_carbs)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it.toInt().toString())
                }
            }
        }
    }
}

@Composable
fun ItemImage(modifier: Modifier = Modifier, size: Dp, imageModel: String? = "") {
    Card(shape = CircleShape, modifier = modifier.size(size)) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_plate),
                contentDescription = stringResource(id = R.string.content_description_icon_plate),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            var isVisible by remember { mutableStateOf(false) }

            imageModel?.let {
                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = if (isVisible) Modifier.size(size.div(2)) else Modifier
                ) {
                    CoilImage(
                        imageModel = { it },
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
}

@Composable
fun DropDownMenu(modifier: Modifier = Modifier, measures: List<Measure>) {
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        measures.forEach {
            DropdownMenuItem(
                text = { it.label },
                onClick = {
                    expanded = false
                }
            )
        }
    }
}

