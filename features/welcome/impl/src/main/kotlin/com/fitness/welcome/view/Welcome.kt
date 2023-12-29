package com.fitness.welcome.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import auth.AuthFailure
import com.fitness.component.components.BodyBalanceImageLogo
import com.fitness.component.screens.ErrorScreen
import com.fitness.component.screens.LoadingScreen
import com.fitness.component.screens.MessageScreen
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.welcome.viewmodel.WelcomeState
import extensions.Dark
import extensions.Light
import extensions.cast
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import state.BaseViewState


@Light
@Dark
@Composable
private fun WelcomePreview() {
    BodyBalanceTheme {
        Surface {
            WelcomeScreen(MutableStateFlow(BaseViewState.Data(WelcomeState())), {})
        }
    }
}

@Composable
fun WelcomeScreen(
    state: StateFlow<BaseViewState<WelcomeState>>,
    onComplete: (Boolean) -> Unit,
) {
    val uiState by state.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            WelcomeScreenContent(
                state = uiState.cast<BaseViewState.Data<WelcomeState>>().value,
                onComplete = onComplete
            )
        }

        is BaseViewState.Error -> {
            val failure = uiState.cast<BaseViewState.Error>().throwable as AuthFailure

            ErrorScreen(title = failure.title, description = failure.description) {
                onComplete(false)
            }
        }

        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        else -> {
            MessageScreen(
                message = R.string.unknown,
                onClick = { onComplete(false) }
            )
        }
    }
}

@Composable
fun WelcomeScreenContent(
    state: WelcomeState,
    onComplete: (Boolean) -> Unit
) {
    var showTopText by remember { mutableStateOf(true) }
    var showBottomText by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = currentPhase) {
        delay(900) // Delay for each phase
        currentPhase++
        if (currentPhase > 2) {
            onComplete(state.isAuthenticated)
        } else {
            showTopText = currentPhase % 2 == 0
            showBottomText = !showTopText
        }
    }


    val topText = when (currentPhase) {
        0 -> "Embark"
        1 -> ""
        else -> "Evolve"
    }

    val bottomText = when (currentPhase) {
        1 -> "Endure"
        else -> ""
    }

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        val offsetY = remember { Animatable(400f) }

        LaunchedEffect(Unit) {
            offsetY.animateTo(
                targetValue = 200f,
                spring(
                    dampingRatio = DampingRatioMediumBouncy,
                    stiffness = StiffnessVeryLow
                ),
            )
        }

        BodyBalanceImageLogo(
            contentScale = ContentScale.Crop, // This will crop the image to fill the bounds
            modifier = Modifier
                .offset(y = offsetY.value.dp)
        )
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        val (topRef, bottomRef) = createRefs()
        Box(modifier = Modifier.constrainAs(topRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top, 50.dp)
        }) {
            AnimatedVisibility(
                visible = showTopText,
                enter = fadeIn(
                    animationSpec = spring(
                        DampingRatioHighBouncy,
                        stiffness = StiffnessVeryLow
                    )
                ),
                exit = fadeOut(
                    animationSpec = spring(
                        DampingRatioHighBouncy,
                        stiffness = StiffnessVeryLow
                    )
                )
            ) {
                Text(
                    text = topText,
                    fontSize = 32.sp,
                )
            }
        }

        Box(modifier = Modifier.constrainAs(bottomRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top, 50.dp)
        }) {
            AnimatedVisibility(
                visible = showBottomText,
                enter = fadeIn(
                    animationSpec = spring(
                        DampingRatioHighBouncy,
                        stiffness = StiffnessVeryLow
                    )
                ),
                exit = fadeOut(
                    animationSpec = spring(
                        DampingRatioHighBouncy,
                        stiffness = StiffnessVeryLow
                    )
                )
            ) {
                Text(
                    text = bottomText,
                    fontSize = 24.sp,
                )
            }
        }
    }
}
