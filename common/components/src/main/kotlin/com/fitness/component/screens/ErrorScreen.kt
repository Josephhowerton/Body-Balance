package com.fitness.component.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.fitness.component.components.ContinueButton
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
@Composable
private fun ErrorScreenPreview() {
    BodyBalanceTheme {
        Surface {
            ErrorScreen(R.string.calories, R.string.calories_compact)
        }
    }
}

@Composable
fun ErrorScreen(
    title: Int,
    description: Int,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_empty_hourglass),
            contentDescription = null,
            tint = Red,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        Text(
            text = stringResource(id = description),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        ContinueButton(onClick)
    }
}

@Light
@Dark
@Composable
fun ErrorDialog(
    title: String = stringResource(id =R.string.title_error),
    description: String = stringResource(id =R.string.desc_error_min_items_required),
    onDismiss: () -> Unit =  {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = description) },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Red),
                onClick = onDismiss
            ) { Text(stringResource(id = R.string.title_dismiss)) }
        }
    )
}