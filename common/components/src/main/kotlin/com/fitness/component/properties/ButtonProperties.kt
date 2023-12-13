package com.fitness.component.properties

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun buttonElevation() = ButtonDefaults.buttonElevation(
    defaultElevation = 5.dp,
    pressedElevation = 10.dp,
)

@Composable
fun buttonShape() = RoundedCornerShape(50.dp)