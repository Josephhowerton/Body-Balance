package com.fitness.component.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fitness.component.components.SquareItem
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.theme.ui.Red
import enums.SystemOfMeasurement
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun PreviewSystemOfMeasurementDialog() = BodyBalanceTheme {
    Surface {
        SystemOfMeasurementDialog()
    }
}

@Composable
fun SystemOfMeasurementDialog(
    onClick: (SystemOfMeasurement) -> Unit = {}
){
    Dialog(onDismissRequest = { onClick(SystemOfMeasurement.METRIC) }) {
        Card {
            Column(Modifier.padding(20.dp)) {
                var measurement by remember{ mutableStateOf(SystemOfMeasurement.METRIC) }

                Text(text = stringResource(id = R.string.select_a_unit_of_measure))

                Spacer(modifier = Modifier.size(25.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    SquareItem(
                        title = stringResource(id = SystemOfMeasurement.METRIC.title),
                        isSelected = measurement == SystemOfMeasurement.METRIC,
                        onClick = {
                            if(measurement != SystemOfMeasurement.METRIC) {
                                measurement = SystemOfMeasurement.METRIC
                            }
                        }
                    )

                    SquareItem(
                        title = stringResource(id = SystemOfMeasurement.CUSTOMARY.title),
                        isSelected = measurement == SystemOfMeasurement.CUSTOMARY,
                        onClick = {
                            if(measurement != SystemOfMeasurement.CUSTOMARY) {
                                measurement = SystemOfMeasurement.CUSTOMARY
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.size(25.dp))

                Text(text = stringResource(id = measurement.description))

                Spacer(modifier = Modifier.size(25.dp))

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {onClick(SystemOfMeasurement.METRIC) }) {
                        Text(text = stringResource(id = R.string.title_dismiss))
                    }

                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {onClick(measurement) }) {
                        Text(text = stringResource(id = R.string.title_continue))
                    }
                }
            }
        }
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
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = onDismiss
            ) { Text(stringResource(id = R.string.title_dismiss)) }
        }
    )
}

@Light
@Dark
@Composable
fun MessageDialog(
    title: String = stringResource(id = R.string.title_error),
    description: String = stringResource(id = R.string.desc_error_min_items_required),
    onContinue: () -> Unit = {},
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
    title: String = stringResource(id = R.string.title_error),
    description: String = stringResource(id = R.string.desc_error_min_items_required),
    onContinue: () -> Unit = {},
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
    title: String = stringResource(id = R.string.title_chefs_corner),
    description: String = stringResource(id = R.string.create_recipe_name_prompt),
    label: String = stringResource(id = R.string.recipe_name_dialog_label),
    onContinue: (String) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var inputText by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = title) },
        text = {
            Column {
                Text(text = description, modifier = Modifier.padding(bottom = 10.dp))

                OutlinedTextField(
                    value = inputText,
                    onValueChange = {
                        inputText = it
                        showError = false
                    },
                    label = { Text(label) },
                    isError = showError,
                    modifier = Modifier.fillMaxWidth()
                )

                if (showError) {
                    Text(
                        text = stringResource(id = R.string.please_enter_a_name),
                        color = Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (inputText.isBlank()) {
                    showError = true
                } else {
                    onContinue(inputText)
                }
            }) {
                Text(stringResource(id = R.string.title_continue))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(stringResource(id = R.string.title_dismiss))
            }
        }
    )
}