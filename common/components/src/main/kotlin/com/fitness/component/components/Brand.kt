package com.fitness.component.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitness.resources.R

@Composable
fun BodyBalanceImageLogo(modifier: Modifier){
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.icon_training),
        contentDescription = stringResource(id = R.string.content_description_training)
    )
}