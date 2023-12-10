package com.fitness.component.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.theme.ui.BodyBalanceTheme

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
@Composable
private fun EmptyScreenPreview() {
    BodyBalanceTheme {
        Surface {
            EmptyScreen()
        }
    }
}

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(com.fitness.resources.R.drawable.icon_empty_hourglass),
            contentDescription = null,
            tint = Red,
            modifier = modifier
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        Text(
            text = stringResource(id = com.fitness.resources.R.string.content_description_analysis),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}