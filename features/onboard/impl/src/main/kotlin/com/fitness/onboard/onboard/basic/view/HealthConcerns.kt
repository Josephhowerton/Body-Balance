package com.fitness.onboard.onboard.basic.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationEvent
import com.fitness.onboard.onboard.basic.viewmodel.BasicInformationState

@Composable
fun HealthConcerns(
    state: BasicInformationState,
    onTriggerEvent: (BasicInformationEvent) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

    }
}

