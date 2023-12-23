package com.fitness.component.components

import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
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
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit = {},
) {
    TextField(
        value = value,
        trailingIcon = trailingIcon,
        shape = textFieldShape(),
        onValueChange = onValueChanged,
        singleLine = true,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        placeholder = { Text(text = stringResource(id = hint)) },
        label = { Text(text = stringResource(id = label)) },
        modifier = modifier.border(1.dp, Color.Black, textFieldShape()),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}