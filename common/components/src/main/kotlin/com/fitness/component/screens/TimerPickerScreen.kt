package com.fitness.component.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme
import extensions.Dark
import extensions.Light
import java.util.Calendar

@Light
@Dark
@Composable
private fun PreviewBalanceTimePicker() = BodyBalanceTheme {
    Surface {
        BalanceTimePicker(onTimePicked = { hour, minute ->
            Log.e(
                "PreviewSimpleCalendar",
                "hour: $hour -- minute:${minute}"
            )
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceTimePicker(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.title_pick_time),
    message: String = stringResource(id = R.string.description_pick_time),
    onTimePicked: (hour: Int, minute: Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = title,
            fontSize = 18.sp,
            modifier = Modifier.padding(20.dp)
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
        )

        Box(contentAlignment = Alignment.BottomEnd) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerState = rememberTimePickerState(
                initialHour = hour,
                initialMinute = minute,
            )

            TimePicker(
                state = timePickerState,
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )

            Button(
                onClick = { onTimePicked(timePickerState.hour, timePickerState.minute) },
                modifier = Modifier.padding(end = 20.dp, bottom = 60.dp)
            ) {
                Text(text = stringResource(id = R.string.title_continue))
            }

        }
    }
}
