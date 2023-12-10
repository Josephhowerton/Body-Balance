package com.fitness.onboard.welcome.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import extensions.Dark
import extensions.Light
import kotlinx.coroutines.delay

@Dark
@Light
@Composable
fun WelcomeScreen(onComplete: () -> Unit = {}) {
    var showTopText by remember { mutableStateOf(true) }
    var showBottomText by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = currentPhase) {
        delay(2000) // Delay for each phase
        currentPhase++
        if(currentPhase > 3){
            onComplete()
        }else{
            showTopText = currentPhase % 2 == 0
            showBottomText = !showTopText
        }
    }

    val topText = when(currentPhase){
        0 -> "Breathe In"
        else -> "Breathe out"
    }

    val bottomText = when(currentPhase){
        1 -> "Focus"
        else -> "Body Balance"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = showTopText,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = topText,
                fontSize = 24.sp,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(150.dp))

        AnimatedVisibility(
            visible = showBottomText,
            enter = fadeIn(),
            exit = fadeOut()) {
            Text(
                text = bottomText,
                fontSize = 24.sp,
                color = Color.Black,
            )
        }
    }
}
