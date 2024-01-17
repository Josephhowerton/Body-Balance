package com.fitness.component.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import enums.EMealType
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun SelectMealTypePreview(onSelect: (EMealType) -> Unit = {}) = BodyBalanceTheme {
    Surface {
        SelectMealType()
    }
}

@Composable
fun SelectMealType(onConfirm: (EMealType) -> Unit = {}) =
    Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxSize()) {

        var showInstructions by remember { mutableStateOf(true) }
        var selected by remember { mutableIntStateOf(-1) }
        var selectedType by remember { mutableStateOf<EMealType?>(null) }

        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(EMealType.values()) { index, item ->
                MealTypeItem(
                    type = item,
                    isSelected = selected == index,
                    onSelect = {
                        selected = index
                        selectedType = item
                    }
                )
            }
        }

        if (selected != -1 && selectedType is EMealType) {
            selectedType?.title?.let {
                MessageDialog(
                    title = stringResource(id = R.string.confirmation_dialog_title),
                    description = stringResource(
                        id = R.string.confirmation_dialog_description,
                        stringResource(id = it)
                    ),
                    onContinue = { onConfirm(selectedType as EMealType) },
                    onCancel = {
                        selected = -1
                        selectedType = null
                    }
                )
            }
        }

        if (showInstructions) {
            MessageDialog(
                title = stringResource(id = R.string.select_meal_type_dialog_title),
                description = stringResource(id = R.string.select_meal_type_description),
                onContinue = { showInstructions = false },
            )
        }
    }

@Composable
fun MealTypeItem(
    type: EMealType,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelect: () -> Unit = {},
) {


    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onSelect()
            }
    ) {

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = if (isSelected) Green else MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(
                topStart = 100.dp,
                bottomStart = 100.dp,
                topEnd = 5.dp,
                bottomEnd = 5.dp
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(start = 5.dp)
                .background(color = MaterialTheme.colorScheme.surface)

        ) {
            AnimatedVisibility(visible = !isSelected) {
                BackgroundImage(type = type)
            }
        }


        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            shape = RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp),
            modifier = modifier.size(150.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { onSelect() }
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Text(
                    text = stringResource(id = type.title),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(10.dp)
                )
            }
        }


    }
}


@Composable
fun BackgroundImage(type: EMealType) {
    val image = when (type) {
        EMealType.BREAKFAST -> R.drawable.image_breakfast
        EMealType.BRUNCH -> R.drawable.image_brunch
        EMealType.SNACK -> R.drawable.image_snack
        EMealType.TEATIME -> R.drawable.image_teatime
        EMealType.LUNCH_DINNER -> R.drawable.image_lunch_dinner
    }

    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth,
    )
}