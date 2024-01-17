package com.fitness.component.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light

@Light
@Dark
@Composable
private fun PreviewSimpleCalendar() = BodyBalanceTheme {
    Surface {
        BalanceDatePicker(onDatesPicked = { Log.e("PreviewSimpleCalendar", it.toString()) })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceDatePicker(modifier: Modifier = Modifier, onDatesPicked: (Long) -> Unit = {}) {
    Box(contentAlignment = Alignment.BottomEnd) {
        var showErrorDialog by remember { mutableStateOf(false) }
        val datePickerState =
            rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

        DatePicker(
            state = datePickerState,
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        )

        Button(onClick = {
            datePickerState.selectedDateMillis?.let(onDatesPicked) ?: kotlin.run {
                showErrorDialog = true
            }
        }, modifier = Modifier.padding(end = 20.dp, bottom = 60.dp)) {
            Text(text = stringResource(id = R.string.title_continue))
        }

        if (showErrorDialog) {
            ErrorDialog(
                title = stringResource(id = R.string.title_date_selection),
                description = stringResource(id = R.string.error_no_date),
                onDismiss = {showErrorDialog = false}
            )
        }

    }
}


