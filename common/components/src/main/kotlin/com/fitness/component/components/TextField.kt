package com.fitness.component.components

import androidx.compose.foundation.border
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.component.properties.textFieldShape
import com.fitness.resources.R

@Preview
@Composable
fun StandardTextFieldPreview() {
    StandardTextField(
        "",
        R.string.enter_email,
        R.string.label_email
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StandardTextField(
    value: String,
    hint: Int,
    label: Int,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChanged: (String) -> Unit = {}
) {
    TextField(
        value = value,
        trailingIcon = trailingIcon,
        shape = textFieldShape(),
        onValueChange = onValueChanged,
        placeholder = { Text(text = stringResource(id = hint)) },
        label = { Text(text = stringResource(id = label)) },
        modifier = modifier.border(1.dp, Color.Black, textFieldShape()),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}