package com.fitness.onboard.onboard.basic.view

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EGender
import extensions.Dark
import extensions.Light
import kotlin.math.abs


@Light
@Dark
@Composable
private fun PreviewGenderAge() = BodyBalanceTheme {
    Surface {
        GenderAge()
    }
}

@Composable
fun GenderAge(onTriggerEvent: (BasicInformationEvent) -> Unit = {}) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            genderTitle,
            genderDesc,
            ageTitle,
            ageDesc,
            maleImage,
            maleCircle,
            femaleImage,
            femaleCircle,
            genderToggle,
            agePicker,
            arrowUp,
            continueButton
        ) = createRefs()

        val topGuide = createGuidelineFromTop(GuidelineProperties.TOP)
        val secondTopGuide = createGuidelineFromTop(GuidelineProperties.SECOND_TOP)
        val midGuide = createGuidelineFromTop(.5f)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)


        createHorizontalChain(maleImage, femaleImage, chainStyle = ChainStyle.Spread)

        var isMale by remember { mutableStateOf(true) }

        val maleColor =
            if (!isMale) Color.Transparent else MaterialTheme.colorScheme.secondaryContainer
        val femaleColor =
            if (isMale) Color.Transparent else MaterialTheme.colorScheme.secondaryContainer

        val gender by remember { mutableStateOf(if (isMale) EGender.MALE else EGender.FEMALE) }
        var age by remember { mutableIntStateOf(18) }

        StandardTitleText(
            text = R.string.select_gender,
            modifier = Modifier.constrainAs(genderTitle) {
                start.linkTo(startGuide)
                top.linkTo(topGuide)
            }
        )

        StandardTextSmall(
            text = R.string.desc_select_gender,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(genderDesc) {
                start.linkTo(startGuide)
                top.linkTo(genderTitle.bottom, 10.dp)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        Box(modifier = Modifier
            .background(maleColor, CircleShape)
            .constrainAs(maleCircle) {
                start.linkTo(maleImage.start, 15.dp)
                end.linkTo(maleImage.end, 15.dp)
                top.linkTo(maleImage.top, 35.dp)
                bottom.linkTo(maleImage.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.icon_man),
            contentDescription = stringResource(id = R.string.content_description_sex_male),
            modifier = Modifier
                .size(120.dp)
                .constrainAs(maleImage) {
                    start.linkTo(startGuide)
                    end.linkTo(femaleImage.start)
                    top.linkTo(secondTopGuide, 20.dp)
                }
        )

        Box(modifier = Modifier
            .background(femaleColor, CircleShape)
            .constrainAs(femaleCircle) {
                start.linkTo(femaleImage.start, 15.dp)
                end.linkTo(femaleImage.end, 15.dp)
                top.linkTo(femaleImage.top, 35.dp)
                bottom.linkTo(femaleImage.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.icon_woman),
            contentDescription = stringResource(id = R.string.content_description_sex_female),
            modifier = Modifier
                .size(120.dp)
                .constrainAs(femaleImage) {
                    start.linkTo(maleImage.end)
                    end.linkTo(endGuide)
                    top.linkTo(secondTopGuide, 20.dp)
                }
        )

        TextToggleSwitch(
            isChecked = isMale,
            onCheckedChange = { isMale = !isMale },
            modifier = Modifier.constrainAs(genderToggle) {
                top.linkTo(maleImage.bottom)
                start.linkTo(maleImage.start)
                end.linkTo(femaleImage.end)
                bottom.linkTo(midGuide)
                width = Dimension.fillToConstraints
            }
        )

        StandardTitleText(
            text = R.string.select_age,
            modifier = Modifier.constrainAs(ageTitle) {
                start.linkTo(startGuide)
                top.linkTo(midGuide, 20.dp)
            }
        )

        StandardTextSmall(
            text = R.string.desc_select_age,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(ageDesc) {
                end.linkTo(endGuide)
                start.linkTo(startGuide)
                top.linkTo(ageTitle.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        AgePickerLazyRow(
            onAgeChanged = { age = it },
            modifier = Modifier.constrainAs(agePicker) {
                top.linkTo(midGuide, 20.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                bottom.linkTo(continueButton.top)
            })

        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.content_description_arrow_up),
            modifier = Modifier.constrainAs(arrowUp) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(agePicker.bottom, 10.dp)
            }
        )

        Button(
            onClick = {
                onTriggerEvent(
                    BasicInformationEvent.GenderAge(
                        gender = gender,
                        age = age
                    )
                )
            },
            modifier = Modifier.constrainAs(continueButton) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

@Composable
private fun TextToggleSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    // Define the base state for animation
    val transitionState = remember { MutableTransitionState(isChecked) }
    transitionState.targetState = isChecked

    BoxWithConstraints(modifier = modifier) {
        // Total width of the container
        val containerWidth = maxWidth

        // Size and padding of the toggle circle
        val toggleSize = 50.dp
        val togglePadding = 8.dp
        val toggleDiameterAndPadding = toggleSize + (togglePadding * 2)

        // Define the transition for the toggle animation
        val transition = updateTransition(transitionState, label = "ToggleSwitch")
        val toggleOffset by transition.animateDp(
            label = "ToggleOffset",
            transitionSpec = {
                if (targetState) {
                    spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                } else {
                    spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                }
            }
        ) { state ->
            if (state)
                0.dp
            else
                containerWidth - toggleDiameterAndPadding
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { onCheckedChange(!isChecked) }
        ) {
            Box(
                modifier = Modifier
                    .padding(togglePadding)
                    .align(Alignment.CenterStart)
                    .offset(toggleOffset)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(toggleSize)
            )
            Text(
                text = if (isChecked) "Male" else "Female",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Light
@Dark
@Composable
private fun TextToggleSwitchPreview() {
    var isChecked by remember { mutableStateOf(true) }
    TextToggleSwitch(
        modifier = Modifier.padding(16.dp),
        isChecked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun AgePickerLazyRow(modifier: Modifier = Modifier, onAgeChanged: (Int) -> Unit = {}) {
    val numbers = (18..100).toMutableList()
    val itemWidth = 100.dp
    val halfItemWidth = itemWidth / 2
    val lazyRowWidth = itemWidth * 3

    numbers.add(numbers.size, 0)
    numbers.add(0, 0)

    val state = rememberLazyListState()
    val centeredNumber = remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier.width(lazyRowWidth),
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    ) {
        items(numbers) { number ->
            Box(
                modifier = Modifier
                    .width(itemWidth)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (number != 0) {
                    val isCentered = number == centeredNumber.intValue
                    Text(
                        text = number.toString(),
                        fontSize = if (isCentered) 22.sp else 16.sp,
                        fontWeight = if (isCentered) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }
    }

    val density = LocalDensity.current

    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val center = lazyRowWidth / 2
                val centeredItem = visibleItems.minByOrNull { visibleItem ->
                    val itemCenter =
                        visibleItem.offset + with(density) { halfItemWidth.roundToPx() }
                    abs(itemCenter - with(density) { center.roundToPx() })
                }
                centeredItem?.let {
                    val newCenteredNumber = numbers[it.index]
                    if (centeredNumber.intValue != newCenteredNumber) {
                        centeredNumber.intValue = newCenteredNumber
                        onAgeChanged(centeredNumber.intValue)
                    }
                }
            }
    }
}
