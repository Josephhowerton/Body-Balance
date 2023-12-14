package com.fitness.component.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light

@Dark
@Light
@Composable
private fun StandardIconPreview(){
    BodyBalanceTheme {
        Surface {
            StandardIcon()
        }
    }
}


@Composable
private fun StandardIcon(){}
