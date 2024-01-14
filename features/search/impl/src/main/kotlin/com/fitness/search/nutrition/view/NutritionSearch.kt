package com.fitness.search.nutrition.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.ItemState
import com.fitness.component.components.NutritionItemWithImage
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.BalanceDatePicker
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.search.nutrition.RecipeStep
import com.fitness.search.nutrition.viewmodel.NutritionSearchEvent
import com.fitness.search.nutrition.viewmodel.NutritionSearchState
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState


@Light
@Dark
@Composable
private fun PreviewNutritionSearch() = BodyBalanceTheme {
    Surface {
        NutritionSearchContent(state = NutritionSearchState(), onTriggerEvent = {})
    }
}

@Composable
fun NutritionSearch(
    state: StateFlow<BaseViewState<NutritionSearchState>>,
    onPopBack: () -> Unit = {},
    onTriggerEvent: (NutritionSearchEvent) -> Unit,
) {

    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            RecipeStep(
                uiState.cast<BaseViewState.Data<NutritionSearchState>>().value,
                onTriggerEvent
            )
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as Failure

            ErrorScreen(title = failure.title, description = failure.description) {
                onPopBack()
            }
        }

        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(message = R.string.unknown, onClick = onPopBack)
        }
    }

}

@Composable
private fun RecipeStep(
    state: NutritionSearchState,
    onTriggerEvent: (NutritionSearchEvent) -> Unit
) {
    when(state.step){
        RecipeStep.PENDING -> NutritionSearchContent(state = state, onTriggerEvent = onTriggerEvent)
        RecipeStep.SELECT_DATE ->  BalanceDatePicker(onDatesPicked = { onTriggerEvent(NutritionSearchEvent.DateSelected(it)) })
        RecipeStep.SELECT_MEAL_TYPE -> {}
        RecipeStep.SAVE -> onTriggerEvent(NutritionSearchEvent.SaveRecipe(state.recipeToSave))
    }
}


@Composable
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
private fun NutritionSearchContent(
    state: NutritionSearchState,
    onTriggerEvent: (NutritionSearchEvent) -> Unit
) {
    val ime = WindowInsets.ime
    val insets = ime.asPaddingValues().calculateBottomPadding()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isImeVisible = WindowInsets.isImeVisible

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = if (isImeVisible) insets else 0.dp)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {}) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {

        val (searchField, autoComplete) = createRefs()

        var search by remember { mutableStateOf("") }
        var isFocused by remember { mutableStateOf(false) }

        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            items(state.searchResults) { recipe ->
                val itemState by remember { mutableStateOf(ItemState.UNSELECTED) }

                val image = recipe.images?.regular?.url
                val title = recipe.label
                val nutrients = recipe.totalNutrients
                val energy = nutrients?.enerckcal
                val fat = nutrients?.fat
                val net = nutrients?.chocdfnet

                if (image != null && title != null) {
                    NutritionItemWithImage(
                        title = title,
                        imageModel = image,
                        itemState = itemState,
                        energy = energy?.quantity,
                        fat = fat?.quantity,
                        net = net?.quantity,
                        onClickAdd = {
                            onTriggerEvent(NutritionSearchEvent.RecipeSelected(recipe))
                        }
                    )
                }
            }

            item { Spacer(modifier = Modifier.size(125.dp)) }
        }

        if (isFocused && search.isNotEmpty() && state.autoComplete.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(40.dp)
                    )
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 65.dp)
                    .constrainAs(autoComplete) {
                        end.linkTo(endGuide)
                        start.linkTo(startGuide)
                        bottom.linkTo(searchField.bottom)
                        width = Dimension.fillToConstraints
                    }
            ) {
                items(state.autoComplete) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable {
                                search = suggestion
                                focusManager.clearFocus()
                                onTriggerEvent(NutritionSearchEvent.Search(search = search))
                            }
                    )
                }
            }
        }

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.content_description_search),
                    modifier = Modifier.clickable {
                        onTriggerEvent(
                            NutritionSearchEvent.Search(
                                search = search
                            )
                        )
                    }
                )
            },
            shape = RoundedCornerShape(40.dp),
            colors = TextFieldDefaults
                .colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            modifier = Modifier
                .shadow(5.dp, RoundedCornerShape(40.dp))
                .onFocusChanged { isFocused = it.isFocused }
                .constrainAs(searchField) {
                    end.linkTo(endGuide)
                    start.linkTo(startGuide)
                    bottom.linkTo(bottomGuide)
                    width = Dimension.fillToConstraints
                }
        )


        LaunchedEffect(key1 = search) {
            if (search.isNotEmpty()) {
                onTriggerEvent(NutritionSearchEvent.AutoComplete(search))
            }
        }
    }
}