package com.fitness.component.screens

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.BalanceItem
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.components.StandardTitleText
import com.fitness.component.isSelected
import com.fitness.component.properties.GuidelineProperties
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EHealthLabel
import extensions.Dark
import extensions.GeneralItem
import extensions.Light


@Light
@Dark
@Composable
private fun PreviewBalanceLazyGrid() = BodyBalanceTheme {
    Surface {
        BalanceLazyColumn(
            title = R.string.title_fitness_habits,
            description = R.string.desc_fitness_habits,
            items = EHealthLabel.values(),
            selections = mutableListOf(),
            onButtonClicked = { Log.e("Preview", it.toString()) }
        )
    }
}

@Composable
fun <T : GeneralItem> BalanceLazyColumn(
    title: Int,
    description: Int,
    items: Array<T>,
    selections: MutableList<T>,
    modifier: Modifier = Modifier,
    onButtonClicked: (List<T>) -> Unit
) = ConstraintLayout(modifier = modifier.fillMaxSize()) {

    val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
    val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

    val (titleRef, listRef, okRef) = createRefs()
    val gridState = rememberLazyGridState()
    val isScrolling = gridState.isScrollInProgress
    LazyVerticalGrid(
        state = gridState,
        modifier = Modifier
            .constrainAs(listRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
        columns = GridCells.Fixed(3)
    ) {
        items(items) { item ->
            BalanceItem(
                item = item,
                selectedState = selections.isSelected(item),
                onClick = {
                    if (selections.contains(it)) {
                        selections.remove(it)
                    } else {
                        selections.add(it)
                    }
                }
            )
        }
    }

    AnimatedVisibility(
        visible = !isScrolling,
        modifier = Modifier.constrainAs(titleRef) {
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
                .constrainAs(titleRef) {
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
                    text = title,
                    modifier = Modifier.padding()
                )

                Spacer(modifier = Modifier.size(10.dp))

                StandardTextSmall(
                    text = description,
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
            .constrainAs(okRef) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
    ) {
        Button(
            onClick = { onButtonClicked(selections) },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .constrainAs(okRef) {
                    end.linkTo(endGuide)
                    bottom.linkTo(bottomGuide)
                }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

