package com.fitness.recipebuilder.screens.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.CircleProgressWithTextComponent
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorDialog
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageDialog
import com.fitness.component.screens.MessageScreen
import com.fitness.data.util.TotalNutrientsKeys
import com.fitness.domain.model.nutrition.Ingredient
import com.fitness.domain.model.nutrition.Measure
import com.fitness.domain.model.nutrition.Qualified
import com.fitness.domain.model.nutrition.Qualifier
import com.fitness.recipebuilder.util.NutritionItem
import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.recipebuilder.util.SmallNutritionItem
import com.fitness.recipebuilder.util.SquareNutritionItem
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Red
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState
import kotlin.math.abs


@Light
@Dark
@Composable
fun MetricSelectionDialogPreview(
    onContinue: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) = BodyBalanceTheme {
    Surface {
        MetricSelectionDialog(measuresForPreview)
    }
}

@Light
@Dark
@Composable
fun ModifyMetricSelectionDialogPreview(
    onContinue: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) = BodyBalanceTheme {
    Surface {
        ModifyMetricSelectionDialog(ingredientForPreview)
    }


}

@Light
@Dark
@Composable
private fun PreviewIngredientSearch() = BodyBalanceTheme {
    Surface {
        IngredientSearchContent(
            state = IngredientSearchState(
                ingredients = ingredientsForPreview,
                searchResults = searchResultsForPreview
            ),
            onTriggerEvent = {}
        )
    }
}


@Light
@Dark
@Composable
private fun PreviewIngredientSearchEmpty() = BodyBalanceTheme {
    Surface {
        IngredientSearchContent(
            state = IngredientSearchState(searchResults = searchResultsForPreview),
            onTriggerEvent = {}
        )
    }
}

@Composable
fun IngredientSearch(
    state: StateFlow<BaseViewState<IngredientSearchState>>,
    onTriggerEvent: (IngredientSearchEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onPickDate: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {

        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<IngredientSearchState>>().value
            if (currentState.step == RecipeBuilderStep.PENDING) {
                IngredientSearchContent(state = currentState, onTriggerEvent = onTriggerEvent)
            } else {
                onPickDate()
            }
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
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
private fun IngredientSearchContent(
    state: IngredientSearchState,
    onTriggerEvent: (IngredientSearchEvent) -> Unit
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

        val (metrics, searchField, ingredients, autoComplete) = createRefs()

        var search by remember { mutableStateOf("") }
        var isFocused by remember { mutableStateOf(false) }

        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM)

        var totalEnergy by remember { mutableDoubleStateOf(0.0) }
        var totalProtein by remember { mutableDoubleStateOf(0.0) }
        var totalFat by remember { mutableDoubleStateOf(0.0) }
        var totalCarbs by remember { mutableDoubleStateOf(0.0) }
        var totalFiber by remember { mutableDoubleStateOf(0.0) }

        var selectedIngredient by remember { mutableStateOf<Ingredient?>(null) }
        val selectedIngredients = remember { state.ingredients.toMutableStateList() }

        var showConfirmDialog by remember { mutableStateOf(false) }
        var showSelectMetricsDialog by remember { mutableStateOf(false) }
        var showModifyMetricsDialog by remember { mutableStateOf(false) }

        SelectedIngredientsSummary(
            ingredients = selectedIngredients,
            onClickModify = {
                selectedIngredient = it
                showModifyMetricsDialog = true
            },
            totalEnergy = totalEnergy,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCarbs = totalCarbs,
            totalFiber = totalFiber,
            modifier = Modifier.constrainAs(metrics) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(ingredients) {
                    top.linkTo(metrics.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item { Spacer(modifier = Modifier.size(10.dp)) }

            items(state.searchResults) {

                val energy = it.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity
                val fat = it.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity
                val protein = it.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity
                val carbs = it.nutrients?.get(TotalNutrientsKeys.KEY_CARBOHYDRATES)?.quantity
                val fiber = it.nutrients?.get(TotalNutrientsKeys.KEY_FIBER)?.quantity
                val net = abs((carbs ?: 0.0) - (fiber ?: 0.0))

                it.name?.let { title ->
                    NutritionItem(
                        title = title,
                        imageModel = it.image,
                        energy = energy,
                        fat = fat,
                        protein = protein,
                        net = net,
                        onClickItem = {
                            selectedIngredient = it
                            if (selectedIngredients.contains(it)) {
                                showModifyMetricsDialog = true
                            } else {
                                showConfirmDialog = true
                            }
                        }
                    )
                }
            }

            item { Spacer(modifier = Modifier.size(75.dp)) }
        }

        AutoComplete(
            autoComplete = state.autoComplete,
            isFocused = isFocused,
            isSearchable = search.isNotEmpty(),
            onClick = {
                search = it
                focusManager.clearFocus()
                onTriggerEvent(IngredientSearchEvent.Search(search = search))
            },
            modifier = Modifier.constrainAs(autoComplete) {
                end.linkTo(endGuide)
                start.linkTo(startGuide)
                bottom.linkTo(searchField.bottom)
                width = Dimension.fillToConstraints
            }
        )

        SearchBox(
            search = search,
            onValueChange = { search = it },
            onClick = { onTriggerEvent(IngredientSearchEvent.Search(search = search)) },
            modifier = Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .constrainAs(searchField) {
                    end.linkTo(endGuide)
                    start.linkTo(startGuide)
                    bottom.linkTo(bottomGuide)
                    width = Dimension.fillToConstraints
                }
        )

        if (showConfirmDialog && selectedIngredient != null) {
            selectedIngredient?.name?.let {
                MessageDialog(
                    title = stringResource(id = R.string.confirmation_dialog_title),
                    description = stringResource(id = R.string.confirmation_dialog_description, selectedIngredient?.name ?: ""),
                    onContinue = {
                        showConfirmDialog = false
                        showSelectMetricsDialog = true
                    },
                    onCancel = { showConfirmDialog = false }
                )
            } ?: run {
                selectedIngredient = null
                showConfirmDialog = false
            }
        }

        if (showSelectMetricsDialog && selectedIngredient != null) {

            selectedIngredient!!.measures?.let {

                MetricSelectionDialog(
                    inMeasures = it,
                    onContinue = { measure, qualifier, quantity ->
                        val ingredient = selectedIngredient!!.copy(
                            measureName = measure.label,
                            measureWeight = measure.weight,
                            measureUri = measure.uri,
                            quantity = quantity,
                            qualifiedName = qualifier?.label,
                            qualifiedWeight = qualifier?.weight,
                            qualifiedUri = qualifier?.uri
                        )

                        totalEnergy += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity ?: 0.0
                        totalFat += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity ?: 0.0
                        totalProtein += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity ?: 0.0
                        totalCarbs += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_CARBOHYDRATES)?.quantity ?: 0.0
                        totalFiber += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_FIBER)?.quantity ?: 0.0

                        selectedIngredients.add(ingredient)
                        showSelectMetricsDialog = false

                    },
                    onDismissRequest = {
                        selectedIngredient = null
                        showSelectMetricsDialog = false
                    }
                )

            } ?: run {

                ErrorDialog(
                    title = stringResource(id = R.string.error_title_incomplete_data),
                    description = stringResource(id = R.string.error_message_incomplete_data),
                    onDismiss = {
                        selectedIngredient = null
                        showSelectMetricsDialog = false
                    }
                )

            }
        }

        if (showModifyMetricsDialog) {

            selectedIngredient?.let {

                val tempEnergy = selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity ?: 0.0
                val tempFat = selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity ?: 0.0
                val tempProtein = selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity ?: 0.0
                val tempCarbs = selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_CARBOHYDRATES)?.quantity ?: 0.0
                val tempFiber = selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_FIBER)?.quantity ?: 0.0

                ModifyMetricSelectionDialog(
                    ingredient = it,
                    onContinue = { measure, qualifier, quantity ->
                        val indexOf = selectedIngredients.indexOf(selectedIngredient)
                        val ingredient = selectedIngredients[indexOf].copy(
                            measureName = measure.label,
                            measureWeight = measure.weight,
                            measureUri = measure.uri,
                            quantity = quantity,
                            qualifiedName = qualifier?.label,
                            qualifiedWeight = qualifier?.weight,
                            qualifiedUri = qualifier?.uri
                        )

                        totalEnergy += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity ?: 0.0
                        totalFat += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity ?: 0.0
                        totalProtein += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity ?: 0.0
                        totalCarbs += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_CARBOHYDRATES)?.quantity ?: 0.0
                        totalFiber += selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_FIBER)?.quantity ?: 0.0

                        totalEnergy -= tempEnergy
                        totalFat -= tempFat
                        totalProtein -= tempProtein
                        totalCarbs -= tempCarbs
                        totalFiber -= tempFiber

                        selectedIngredients.remove(selectedIngredient)
                        selectedIngredients.add(ingredient)
                        showModifyMetricsDialog = false
                    },
                    onDismiss = {
                        selectedIngredient = null
                        showModifyMetricsDialog = false
                    },
                    onRemove = {
                        totalEnergy -= selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity ?: 0.0
                        totalFat -= selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity ?: 0.0
                        totalProtein -= selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity ?: 0.0
                        totalCarbs -= selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_CARBOHYDRATES)?.quantity ?: 0.0
                        totalFiber -= selectedIngredient?.nutrients?.get(TotalNutrientsKeys.KEY_FIBER)?.quantity ?: 0.0

                        selectedIngredients.remove(selectedIngredient)
                        selectedIngredient = null
                        showModifyMetricsDialog = false
                    }
                )

            } ?: run {

                ErrorDialog(
                    title = stringResource(id = R.string.error_title_incomplete_data),
                    description = stringResource(id = R.string.error_message_incomplete_data),
                    onDismiss = { showModifyMetricsDialog = false }
                )

            }
        }

        LaunchedEffect(key1 = search) {
            if (search.isNotEmpty()) {
                onTriggerEvent(IngredientSearchEvent.AutoComplete(search))
            }
        }
    }
}

@Composable
private fun AutoComplete(
    isFocused: Boolean,
    isSearchable: Boolean,
    autoComplete: List<String>,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    if (isFocused && isSearchable && autoComplete.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(40.dp)
                )
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 65.dp)
        ) {
            items(autoComplete) { suggestion ->
                Text(
                    text = suggestion,
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            onClick(suggestion)
                        }
                )
            }
        }
    }
}

@Composable
private fun SearchBox(
    search: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.content_description_search),
                modifier = Modifier.clickable { onClick() }
            )
        },
        shape = RoundedCornerShape(40.dp),
        colors = TextFieldDefaults
            .colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        modifier = modifier.shadow(2.dp, RoundedCornerShape(40.dp))
    )
}

@Composable
private fun SelectedIngredientsSummary(
    ingredients: List<Ingredient>,
    modifier: Modifier,
    onClickModify: (Ingredient) -> Unit,
    totalEnergy: Double? = 45.0,
    totalProtein: Double? = 15.0,
    totalFat: Double? = 35.0,
    totalCarbs: Double? = 85.0,
    totalFiber: Double? = 15.0,

    targetEnergy: Double? = 100.0,
    targetProtein: Double? = 100.0,
    targetFat: Double? = 100.0,
    targetNet: Double? = 100.0,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier.size(150.dp)
    ) {
        if (ingredients.isNotEmpty()) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                val (ingredientsRef, metricsRef) = createRefs()

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                    .padding()
                    .constrainAs(ingredientsRef) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                    }) {

                    item { Spacer(modifier = Modifier.size(2.dp)) }

                    items(ingredients) { ingredient ->
                        ingredient.name?.let { title ->
                            SmallNutritionItem(
                                title = title,
                                onClickModify = { onClickModify(ingredient) }
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.size(2.dp)) }
                }

                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .constrainAs(metricsRef) {
                            top.linkTo(ingredientsRef.bottom)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    if (totalEnergy != null && targetEnergy != null) {
                        CircleProgressWithTextComponent(
                            title = stringResource(id = R.string.calories),
                            currentValue = totalEnergy,
                            targetValue = targetEnergy,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    if (totalProtein != null && targetProtein != null) {
                        CircleProgressWithTextComponent(
                            title = stringResource(id = R.string.protein),
                            currentValue = totalProtein,
                            targetValue = targetProtein,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    if (totalFat != null && targetFat != null) {
                        CircleProgressWithTextComponent(
                            title = stringResource(id = R.string.fat),
                            currentValue = totalFat,
                            targetValue = targetFat,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    val totalNet = totalCarbs?.let { carbs ->
                        totalFiber?.let { fiber ->
                            carbs - fiber
                        }
                    }

                    if (totalNet != null && targetNet != null) {
                        CircleProgressWithTextComponent(
                            title = stringResource(id = R.string.net_carbs),
                            currentValue = totalNet,
                            targetValue = targetNet,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.ingredient_metrics_prompt),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MetricSelectionDialog(
    inMeasures: List<Measure>,
    onContinue: (measure: Measure, qualifier: DisplayQualifier?, quantity: Double) -> Unit = { _, _, _ -> },
    onDismissRequest: () -> Unit = {}
) {
    var showError by remember { mutableStateOf(false) }
    var errorId by remember { mutableIntStateOf(-1) }
    var quantityText by remember { mutableStateOf("") }

    val measures = remember { inMeasures.toMutableList() }
    var qualifiers = remember { listOf<DisplayQualifier>() }
    var selectedMeasure by remember { mutableStateOf<Measure?>(null) }
    var selectedQualifier by remember { mutableStateOf<DisplayQualifier?>(null) }

    var selectedMeasureIndexed by remember { mutableIntStateOf(-1) }
    var selectedQualifierIndexed by remember { mutableIntStateOf(-1) }

    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = stringResource(id = R.string.select_base_measure))

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item { Spacer(modifier = Modifier.size(1.dp)) }

                    itemsIndexed(measures) { indexed, measure ->
                        measure.label?.let {
                            SquareNutritionItem(
                                title = it,
                                isSelected = selectedMeasureIndexed == indexed,
                                onClick = {
                                    selectedMeasure = measure
                                    selectedMeasureIndexed = indexed
                                    qualifiers = measure.qualified?.flatMap { qualified ->
                                        qualified.qualifiers?.map { qualifier ->
                                            DisplayQualifier(
                                                qualifier.label,
                                                qualifier.uri,
                                                qualified.weight
                                            )
                                        } ?: run {
                                            emptyList()
                                        }
                                    } ?: run {
                                        emptyList()
                                    }
                                }
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.size(1.dp)) }
                }

                val qualifierVisibilityState = remember { MutableTransitionState(false) }

                AnimatedVisibility(visibleState = qualifierVisibilityState) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(text = stringResource(id = R.string.select_qualified_measure))

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            item { Spacer(modifier = Modifier.size(1.dp)) }

                            itemsIndexed(qualifiers) { indexed, qualifier ->
                                qualifier.label?.let {
                                    SquareNutritionItem(
                                        title = it,
                                        isSelected = selectedQualifierIndexed == indexed,
                                        onClick = {
                                            selectedQualifierIndexed = indexed
                                            selectedQualifier = qualifier
                                        }
                                    )
                                }
                            }

                            item { Spacer(modifier = Modifier.size(1.dp)) }

                        }
                    }
                }

                val quantityVisibilityState = remember { MutableTransitionState(false) }

                AnimatedVisibility(visibleState = quantityVisibilityState) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(
                                id = R.string.enter_base_measure_amount,
                                selectedMeasure?.label ?: ""
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = quantityText,
                            onValueChange = {
                                quantityText = it
                                showError = false
                            },
                            isError = showError,
                        )

                        if (showError && errorId != -1) {
                            Text(
                                text = stringResource(id = errorId),
                                color = Red,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(50.dp))

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismissRequest) {
                        Text(text = stringResource(id = R.string.title_dismiss))
                    }

                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {
                        if (selectedMeasure != null && (selectedQualifier != null || qualifiers.isEmpty()) && quantityText.toDoubleOrNull() != null) {
                            onContinue(
                                selectedMeasure!!,
                                selectedQualifier,
                                quantityText.toDouble()
                            )
                        } else {
                            showError = true
                            errorId = determineErrorMessage(
                                selectedMeasure,
                                selectedQualifier,
                                quantityText
                            )
                        }
                    }) {
                        Text(text = stringResource(id = R.string.title_continue))
                    }
                }

                LaunchedEffect(key1 = selectedMeasure, selectedQualifier) {
                    qualifierVisibilityState.targetState =
                        selectedMeasure != null && qualifiers.isNotEmpty()
                    quantityVisibilityState.targetState =
                        selectedMeasure != null && (selectedQualifier != null || qualifiers.isEmpty())
                }
            }
        }
    }
}

@Composable
fun ModifyMetricSelectionDialog(
    ingredient: Ingredient,
    onContinue: (measure: Measure, qualifier: DisplayQualifier?, quantity: Double) -> Unit = { _, _, _ -> },
    onDismiss: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    var showError by remember { mutableStateOf(false) }
    var errorId by remember { mutableIntStateOf(-1) }

    var quantityText by remember { mutableStateOf( ingredient.quantity.toString() ) }

    val measures = remember { ingredient.measures ?: emptyList() }

    val selectedMeasureIndex = measures.indexOfFirst { it.label == ingredient.measureName }
    val initialSelectedMeasure = selectedMeasureIndex.let { measures[it] }

    var selectedMeasure by remember { mutableStateOf(initialSelectedMeasure) }
    var selectedMeasureIndexed by remember { mutableIntStateOf(selectedMeasureIndex) }

    var qualifiers = remember(selectedMeasure) {
        selectedMeasure.qualified?.flatMap { qualified ->
            qualified.qualifiers?.map { qualifier ->
                DisplayQualifier(qualifier.label, qualifier.uri, qualified.weight)
            } ?: emptyList()
        } ?: emptyList()
    }

    val selectedQualifierIndex = qualifiers.indexOfFirst { it.label == ingredient.qualifiedName }
    val initialSelectedQualifier = selectedQualifierIndex.takeIf { it >= 0 }?.let { qualifiers[it] }

    var selectedQualifier by remember { mutableStateOf(initialSelectedQualifier) }
    var selectedQualifierIndexed by remember { mutableIntStateOf(selectedQualifierIndex) }

    val measuresState = rememberLazyListState()
    val qualifiersState = rememberLazyListState()

    LaunchedEffect(selectedMeasureIndexed) {
        measuresState.scrollToItem(selectedMeasureIndexed)
    }

    LaunchedEffect(selectedQualifierIndexed) {
        qualifiersState.scrollToItem(selectedQualifierIndexed)
    }

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = stringResource(id = R.string.select_base_measure))

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    state = measuresState,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item { Spacer(modifier = Modifier.size(1.dp)) }

                    itemsIndexed(measures) { indexed, measure ->
                        measure.label?.let { name ->
                            SquareNutritionItem(
                                title = name,
                                isSelected = selectedMeasureIndexed == indexed,
                                onClick = {
                                    selectedMeasure = measure
                                    selectedMeasureIndexed = indexed
                                    qualifiers = measure.qualified?.flatMap { qualified ->
                                        qualified.qualifiers?.map { qualifier ->
                                            DisplayQualifier(qualifier.label, qualifier.uri, qualified.weight)
                                        } ?: run {
                                            emptyList()
                                        }
                                    } ?: run {
                                        emptyList()
                                    }
                                }
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.size(1.dp)) }
                }

                val qualifierVisibilityState = remember { MutableTransitionState(false) }

                AnimatedVisibility(visibleState = qualifierVisibilityState) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(text = stringResource(id = R.string.select_qualified_measure))

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyRow(
                            state = qualifiersState,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            item { Spacer(modifier = Modifier.size(1.dp)) }

                            itemsIndexed(qualifiers) { indexed, qualifier ->
                                qualifier.label?.let {
                                    SquareNutritionItem(
                                        title = it,
                                        isSelected = selectedQualifierIndexed == indexed,
                                        onClick = {
                                            selectedQualifierIndexed = indexed
                                            selectedQualifier = qualifier
                                        }
                                    )
                                }
                            }

                            item { Spacer(modifier = Modifier.size(1.dp)) }

                        }
                    }
                }

                val quantityVisibilityState = remember { MutableTransitionState(false) }

                AnimatedVisibility(visibleState = quantityVisibilityState) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(
                                id = R.string.enter_base_measure_amount,
                                selectedMeasure.label ?: ""
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = quantityText,
                            onValueChange = {
                                quantityText = it
                                showError = false
                            },
                            isError = showError,
                        )

                        if (showError && errorId != -1) {
                            Text(
                                text = stringResource(id = errorId),
                                color = Red,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text(text = stringResource(id = R.string.title_dismiss))
                    }

                    Spacer(modifier = Modifier.size(20.dp))

                    Button(
                        onClick = onRemove,
                        colors = ButtonDefaults.buttonColors(containerColor = Red)
                    ) {
                        Text(text = stringResource(id = R.string.title_remove))
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedButton(modifier = Modifier.fillMaxWidth().padding(15.dp), onClick = {
                    if (quantityText.toDoubleOrNull() != null) {
                        onContinue(selectedMeasure, selectedQualifier, quantityText.toDouble())
                    } else {
                        showError = true
                        errorId = determineErrorMessage(
                            selectedMeasure,
                            selectedQualifier,
                            quantityText
                        )
                    }
                }) {
                    Text(text = stringResource(id = R.string.title_continue))
                }

                LaunchedEffect(key1 = selectedMeasure, selectedQualifier) {
                    qualifierVisibilityState.targetState = qualifiers.isNotEmpty()
                    quantityVisibilityState.targetState = qualifiers.isEmpty()
                }
            }
        }
    }
}


private val ingredientsForPreview = listOf(
    Ingredient(name = "Burger"),
    Ingredient(name = "Buns"),
    Ingredient(name = "Lettuce"),
    Ingredient(name = "Pepper"),
    Ingredient(name = "Salt"),
    Ingredient(name = "Cheese"),
    Ingredient(name = "Tomato"),
    Ingredient(name = "Mayonnaise")
)
private val searchResultsForPreview = listOf(
    Ingredient(name = "Burger"),
    Ingredient(name = "Buns"),
    Ingredient(name = "Lettuce"),
    Ingredient(name = "Pepper"),
    Ingredient(name = "Salt"),
    Ingredient(name = "Cheese"),
    Ingredient(name = "Tomato"),
    Ingredient(name = "Mayonnaise"),
    Ingredient(name = "Brisket"),
    Ingredient(name = "Linguica"),
    Ingredient(name = "Chorizo"),
    Ingredient(name = "Chicken"),
    Ingredient(name = "Whole Chicken"),
    Ingredient(name = "Beef Ribs"),
    Ingredient(name = "SpareRibs"),
    Ingredient(name = "Rice")
)
val measuresForPreview = listOf(
    Measure(
        label = "Slice",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_slice"
    ),
    Measure(
        label = "Whole",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_unit"
    ),
    Measure(
        label = "Block",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_block"
    ),
    Measure(
        label = "Piece",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_piece"
    ),
    Measure(
        label = "Serving",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
    ),
    Measure(
        label = "Slice",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_slice"
    ),
    Measure(
        label = "Whole",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_unit"
    ),
    Measure(
        label = "Block",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_block"
    ),
    Measure(
        label = "Piece",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_piece"
    ),
    Measure(
        label = "Serving",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
    ),
    Measure(
        label = "Slice",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_slice"
    ),
    Measure(
        label = "Whole",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_unit"
    ),
    Measure(
        label = "Block",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_block"
    ),
    Measure(
        label = "Piece",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_piece"
    ),
    Measure(
        label = "Serving",
        weight = 28.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
    ),
    Measure(
        label = "Cup",
        weight = 132.0,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
        qualified = listOf(
            Qualified(
                weight = 132.0,
                qualifiers = listOf(
                    Qualifier(
                        label = "diced",
                        uri = "http://www.edamam.com/ontologies/edamam.owl#Qualifier_diced"
                    )
                )
            ),
            Qualified(
                weight = 113.0,
                qualifiers = listOf(
                    Qualifier(
                        label = "shredded",
                        uri = "http://www.edamam.com/ontologies/edamam.owl#Qualifier_shredded"
                    )
                )
            ),
            Qualified(
                weight = 244.0,
                qualifiers = listOf(
                    Qualifier(
                        label = "melted",
                        uri = "http://www.edamam.com/ontologies/edamam.owl#Qualifier_melted"
                    )
                )
            )
        )
    ),
    Measure(
        label = "Ounce",
        weight = 28.349523125,
        uri = "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce"
    )
)

data class DisplayQualifier(
    val label: String?,
    val uri: String?,
    val weight: Double?
)

private val ingredientForPreview = Ingredient(
    name = "Burger",
    qualifiedUri = "http://www.edamam.com/ontologies/edamam.owl#Qualifier_shredded",
    qualifiedName = "shredded",
    qualifiedWeight = 113.0,
    measureUri = "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
    measureName = "Cup",
    measureWeight = 132.0,
    measures = measuresForPreview
)


private fun determineErrorMessage(
    selectedMeasure: Measure?,
    selectedQualifier: DisplayQualifier?,
    quantityText: String?
): Int {
    return if (selectedMeasure == null) {
        R.string.error_measure_not_selected
    } else if (selectedQualifier == null) {
        R.string.error_qualifier_not_selected
    } else if (quantityText == null || quantityText.toDoubleOrNull() == null) {
        R.string.error_invalid_quantity
    } else {
        R.string.error_unknown
    }
}



