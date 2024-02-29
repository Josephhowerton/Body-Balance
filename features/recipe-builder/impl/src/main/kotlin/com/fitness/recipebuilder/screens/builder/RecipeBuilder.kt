package com.fitness.recipebuilder.screens.builder

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.data.util.TotalNutrientsKeys
import com.fitness.recipebuilder.util.NutritionItem
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import enums.EMealType
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Light
@Dark
@Composable
private fun PreviewRecipeBuilder() = BodyBalanceTheme {
    Surface {
        RecipeBuilderContent(
            state = RecipeBuilderState("Hamburger Surprise"),
            onTriggerEvent = {},
            ingredientSearch = {})
    }
}

@Light
@Dark
@Composable
fun PreviewNutritionItem() = BodyBalanceTheme {
    Surface {
        NutritionItem()
    }
}

@Composable
fun RecipeBuilder(
    state: StateFlow<BaseViewState<RecipeBuilderState>>,
    onTriggerEvent: (RecipeBuilderEvent) -> Unit,
    onPopBack: () -> Unit = {},
    onIngredientSearch: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {

        is BaseViewState.Data -> {
            RecipeBuilderContent(
                state = uiState.cast<BaseViewState.Data<RecipeBuilderState>>().value,
                onTriggerEvent = onTriggerEvent,
                ingredientSearch = onIngredientSearch
            )
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().failure as Failure

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
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
private fun RecipeBuilderContent(
    state: RecipeBuilderState,
    onTriggerEvent: (RecipeBuilderEvent) -> Unit,
    ingredientSearch: () -> Unit,
) {
    val ime = WindowInsets.ime
    val insets = ime.asPaddingValues().calculateBottomPadding()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isImeVisible = WindowInsets.isImeVisible
    val scrollState = rememberScrollState()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = if (isImeVisible) insets else 0.dp)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {}) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {



        var name by remember { mutableStateOf(state.name) }
        var date by remember { mutableStateOf(state.date) }
        var type by remember { mutableStateOf(state.type) }
        val ingredients = remember { state.ingredients }
        val instructions = remember { state.instructions }

        val (
            titleRef,
            imageRef,
            addIngredientsRef,
            ingredientsTitleRef,
            ingredientsCountRef,
            ingredientsListRef,
            instructionsTitleRef,
            instructionsListRef,
            createInstructionsRef
        ) = createRefs()

        val topGuide = createGuidelineFromTop(25.dp)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)

        Text(
            text = name,
            fontSize = 22.sp,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        Card(
            modifier = Modifier
                .height(250.dp)
                .constrainAs(imageRef) {
                    top.linkTo(titleRef.bottom, 20.dp)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    width = Dimension.fillToConstraints
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_placeholder),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = stringResource(id = R.string.title_ingredients),
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(ingredientsTitleRef) {
                top.linkTo(imageRef.bottom, 40.dp)
                start.linkTo(startGuide)
                end.linkTo(ingredientsCountRef.start)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = stringResource(id = R.string.ingredients_count, ingredients.size),
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(ingredientsCountRef) {
                top.linkTo(ingredientsTitleRef.top)
                end.linkTo(endGuide)
                start.linkTo(ingredientsTitleRef.end)
                width = Dimension.fillToConstraints
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .constrainAs(ingredientsListRef) {
                    top.linkTo(ingredientsTitleRef.bottom, 20.dp)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    width = Dimension.fillToConstraints
                }
        ) {

            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            items(ingredients) { ingredient ->
                ingredient.name?.let { name ->
                    NutritionItem(
                        title = name,
                        energy = ingredient.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity,
                        fat = ingredient.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity,
                        protein = ingredient.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity,
                        net = ingredient.nutrients?.get(TotalNutrientsKeys.KEY_NET_CARBS)?.quantity,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(25.dp))
            }
        }

        Card(
            shape = RoundedCornerShape(5.dp),
            onClick = { ingredientSearch() },
            modifier = Modifier.constrainAs(addIngredientsRef) {
                end.linkTo(endGuide)
                bottom.linkTo(ingredientsListRef.bottom)
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_add_more),
                    contentDescription = stringResource(id = R.string.content_description_icon_add),
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = stringResource(id = R.string.title_add_more))

            }
        }


        Text(
            text = stringResource(id = R.string.title_instructions),
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(instructionsTitleRef) {
                top.linkTo(addIngredientsRef.bottom, 50.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        if (instructions.isEmpty()) {
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = { onTriggerEvent(RecipeBuilderEvent.GenerateInstructions) },
                modifier = Modifier.constrainAs(instructionsListRef) {
                    end.linkTo(endGuide)
                    bottom.linkTo(instructionsTitleRef.bottom)
                }
            ) {
                Text(text = stringResource(id = R.string.title_generate))
            }

            Text(
                text = stringResource(id = R.string.empty_instructions_message),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(createInstructionsRef) {
                    top.linkTo(instructionsTitleRef.bottom, 40.dp)
                    bottom.linkTo(parent.bottom, 50.dp)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    width = Dimension.fillToConstraints
                }
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .height(250.dp)
                    .constrainAs(instructionsListRef) {
                        top.linkTo(instructionsTitleRef.bottom, 20.dp)
                        start.linkTo(startGuide)
                        end.linkTo(endGuide)
                        width = Dimension.fillToConstraints
                    }
            ) {
                items(EMealType.values()) {
                    NutritionItem()
                }
            }
        }
    }
}
