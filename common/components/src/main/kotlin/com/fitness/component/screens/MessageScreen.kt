package com.fitness.component.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.component.components.ContinueButton
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.resources.R

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
@Composable
private fun MessageScreenPreview() {
    BodyBalanceTheme {
        Surface {
            MessageScreen(message = R.string.desc_reset_password_email_confirmation)
        }
    }
}

@Composable
fun MessageScreen(message: Int,
                modifier: Modifier = Modifier,
                onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = message),
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().padding(50.dp)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        ContinueButton(onClick)
    }
}


