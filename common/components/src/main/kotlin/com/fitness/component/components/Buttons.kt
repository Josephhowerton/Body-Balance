package com.fitness.component.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light


@Dark
@Light
@Composable
private fun ElevatedButtonLightPreview(){
    BodyBalanceTheme {
        Surface {
            StandardIconButton(
                R.drawable.icon_google_logo, 
                R.string.content_description_google
            )
        }
    }
}


@Composable
private fun StandardIconButton(
    icon: Int,
    desc: Int,
    onClick: () -> Unit = {}
    
){
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(id = icon), contentDescription = stringResource(id = desc))
    }
}

