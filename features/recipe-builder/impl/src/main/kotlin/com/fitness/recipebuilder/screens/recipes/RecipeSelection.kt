package com.fitness.recipebuilder.screens.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.component.screens.PromptUserDialog
import com.fitness.data.util.TotalNutrientsKeys
import com.fitness.recipebuilder.navigation.RecipeBuilderEntryImpl
import com.fitness.recipebuilder.screens.builder.RecipeBuilderState
import com.fitness.recipebuilder.util.NutritionItem
import com.fitness.recipebuilder.util.RecipeSelectionStep
import com.fitness.resources.R
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
private fun PreviewRecipeSelection() = BodyBalanceTheme {
    Surface {
        RecipeSelectionContent(state = RecipeSelectionState())
    }
}

@Composable
fun RecipeSelection(
    state: StateFlow<BaseViewState<RecipeBuilderState>>,
    onTriggerEvent: (RecipeSelectionEvent) -> Unit = {},
    onNavigate: (destination: String) -> Unit = {},
    onPopBack: () -> Unit = {}
) {

    val uiState by state.collectAsState()

    when (uiState) {

        is BaseViewState.Data -> {
            RecipeSelectionContent(
                state = uiState.cast<BaseViewState.Data<RecipeSelectionState>>().value,
                onTriggerEvent = onTriggerEvent,
                onNavigate = onNavigate
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
private fun RecipeSelectionContent(
    state: RecipeSelectionState,
    onTriggerEvent: (RecipeSelectionEvent) -> Unit = {},
    onNavigate: (destination: String) -> Unit = {}
) {

    when(state.step){

        RecipeSelectionStep.PENDING -> {
            val myRecipes = remember { state.myRecipes.toMutableList() }
            if (myRecipes.isEmpty()) {
                CreateFirstRecipe(onTriggerEvent = onTriggerEvent)
            } else {
                MyRecipes(state = state, onTriggerEvent = onTriggerEvent)
            }
        }

        RecipeSelectionStep.NAME -> PromptUserDialog(
            title = stringResource(id = R.string.recipe_name_dialog_title),
            description = stringResource(id = R.string.recipe_name_dialog_message),
            label = stringResource(id = R.string.recipe_name_dialog_label),
            onContinue = { onTriggerEvent(RecipeSelectionEvent.GiveName(name = it)) },
            onCancel = { onTriggerEvent(RecipeSelectionEvent.CancelCreate) },
        )

        RecipeSelectionStep.CREATE -> onNavigate(RecipeBuilderEntryImpl.INGREDIENTS)

        RecipeSelectionStep.MODIFY -> onNavigate(RecipeBuilderEntryImpl.RECIPE_BUILDER)

    }


}

@Composable
private fun CreateFirstRecipe(onTriggerEvent: (RecipeSelectionEvent) -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { onTriggerEvent(RecipeSelectionEvent.CreateNewRecipe) }, modifier = Modifier
                .padding(start = 50.dp, end = 50.dp, top = 50.dp)
                .size(100.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_add_more),
                contentDescription = stringResource(id = R.string.content_description_icon_add),
            )
        }

        Text(
            text = stringResource(id = R.string.empty_recipe_book_message),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )
    }
}

@Composable
private fun MyRecipes(
    state: RecipeSelectionState,
    onTriggerEvent: (RecipeSelectionEvent) -> Unit = {}
) {
    Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            items(state.myRecipes) {
                it.recipe.name?.let { title ->
                    NutritionItem(
                        title = title,
                        energy = it.recipe.nutrients?.get(TotalNutrientsKeys.KEY_ENERGY)?.quantity,
                        fat = it.recipe.nutrients?.get(TotalNutrientsKeys.KEY_TOTAL_FAT)?.quantity,
                        protein = it.recipe.nutrients?.get(TotalNutrientsKeys.KEY_PROTEIN)?.quantity,
                        net = it.recipe.nutrients?.get(TotalNutrientsKeys.KEY_NET_CARBS)?.quantity,
                        onClickItem = { onTriggerEvent(RecipeSelectionEvent.RecipeSelected(it)) }
                    )
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = CircleShape,
            modifier = Modifier.padding(end = 25.dp, bottom = 40.dp)
        ) {
            IconButton(
                onClick = { onTriggerEvent(RecipeSelectionEvent.CreateNewRecipe) },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_add_more),
                    contentDescription = stringResource(id = R.string.content_description_icon_add),
                )
            }
        }
    }
}
