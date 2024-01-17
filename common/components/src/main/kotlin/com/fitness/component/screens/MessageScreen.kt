package com.fitness.component.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.component.components.ContinueButton
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.resources.R
import com.fitness.theme.ui.Green
import extensions.Dark
import extensions.Light

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
fun MessageScreen(message: Int, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
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

@Composable
fun MessageScreen(message: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().padding(50.dp)
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
fun MessageDialog(
    title: String = stringResource(id =R.string.title_error),
    description: String = stringResource(id =R.string.desc_error_min_items_required),
    onContinue: () -> Unit =  {},
    onCancel: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = title) },
        text = { Text(text = description) },
        confirmButton = {
            Button(onClick = onContinue) { Text(stringResource(id = R.string.title_continue)) }
        },
        dismissButton = {
            Button(onClick = onCancel) { Text(stringResource(id = R.string.title_dismiss)) }
        }
    )
}

@Light
@Dark
@Composable
fun MessageDialog(
    title: String = stringResource(id =R.string.title_error),
    description: String = stringResource(id =R.string.desc_error_min_items_required),
    onContinue: () -> Unit =  {},
) {
    AlertDialog(
        onDismissRequest = onContinue,
        title = { Text(text = title) },
        text = { Text(text = description) },
        confirmButton = {
            Button(onClick = onContinue) { Text(stringResource(id = R.string.title_dismiss)) }
        },
    )
}

@Light
@Dark
@Composable
fun PromptUserDialog(
    title: String = stringResource(id = R.string.create_recipe_name_title),
    description: String = stringResource(id = R.string.create_recipe_name_prompt),
    onContinue: (String) -> Unit = {},
) {
    var inputText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onContinue(inputText) },
        title = { Text(text = title) },
        text = {
            Column {
                Text(text = description, modifier = Modifier.padding(bottom = 10.dp))

                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Recipe Name") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onContinue(inputText) }) {
                Text(stringResource(id = R.string.title_dismiss))
            }
        },
    )
}



