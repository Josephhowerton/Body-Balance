package com.fitness.authentication.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.authentication.home.viewmodel.CheckBoxState
import com.fitness.authentication.home.viewmodel.Direction
import com.fitness.authentication.home.viewmodel.HomeEvent
import com.fitness.authentication.home.viewmodel.HomeState
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signInEmail
import com.fitness.authentication.navigation.AuthEntryImpl.Companion.signUp
import com.fitness.authentication.util.TermsAndPrivacyAnnotatedText
import com.fitness.component.components.StandardButton
import com.fitness.component.properties.GuidelineProperties
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Green
import com.fitness.theme.ui.Red
import extensions.Dark
import extensions.Light
import extensions.cast
import failure.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState

@Dark
@Light
@Composable
private fun AcceptTermsAndPrivacy() = BodyBalanceTheme {
    Surface {
        AcceptTermsAndPrivacyContent()
    }
}

@Composable
fun AcceptTermsAndPrivacy(
    state: StateFlow<BaseViewState<HomeState>> = MutableStateFlow(BaseViewState.Data(HomeState())),
    onPopBack: () -> Unit = {},
    onTriggerEvent: (HomeEvent) -> Unit = {},
    onTriggerNavigation: (String) -> Unit = {}
) {
    val uiState by state.collectAsState()

    when (uiState) {

        is BaseViewState.Data -> {
            val currentState = uiState.cast<BaseViewState.Data<HomeState>>().value
            if (currentState.direction != Direction.NONE && currentState.hasAgreed) {
                if (currentState.direction == Direction.SIGN_IN) {
                    onTriggerNavigation(signInEmail)
                } else {
                    onTriggerNavigation(signUp)
                }
            }
            else {
                AcceptTermsAndPrivacyContent(state = currentState, onTriggerEvent = onTriggerEvent)
            }
        }

        is Error -> {
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
private fun AcceptTermsAndPrivacyContent(
    state: HomeState = HomeState(), onTriggerEvent: (HomeEvent) -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (
            checkmark,
            signUp,
            signIn,
        ) = createRefs()

        val bottomGuideline = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        val colors = when (state.checkBoxState) {
            CheckBoxState.ERROR -> {
                CheckboxDefaults.colors(checkedColor = Green, uncheckedColor = Red)
            }

            CheckBoxState.OK -> {
                CheckboxDefaults.colors(checkedColor = Green)
            }

            CheckBoxState.NONE -> {
                CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.constrainAs(checkmark){
                bottom.linkTo(signIn.top, 50.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        ) {

            Checkbox(
                checked = state.hasAgreed,
                onCheckedChange = {
                    onTriggerEvent(HomeEvent.CheckBoxChanged(it))
                },
                colors = colors
            )

            TermsAndPrivacyAnnotatedText(
                onClickTerms = { },
                onClickPrivacy = { }
            )
        }


        StandardButton(
            text = R.string.sign_in,
            onClick = { onTriggerEvent(HomeEvent.SignIn(state.hasAgreed)) },
            modifier = Modifier.constrainAs(signIn){
                bottom.linkTo(signUp.top, 20.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.sign_up,
            onClick = { onTriggerEvent(HomeEvent.SignUp(state.hasAgreed)) },
            modifier = Modifier.constrainAs(signUp){
                bottom.linkTo(bottomGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )
    }
}