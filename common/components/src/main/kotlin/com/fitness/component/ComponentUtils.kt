package com.fitness.component

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.fitness.resources.R
import enums.EMealType
import extensions.GeneralItem
import java.io.IOException

@Composable
fun WindIcon() {
    val animatable = remember { Animatable(0f) }

    // Start the animation
    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = TweenSpec(durationMillis = 600) // Adjust duration as needed
        )
    }

    val context = LocalContext.current
    val drawable =
        ResourcesCompat.getDrawable(context.resources, R.drawable.icon_wind, context.theme)
    val bitmap = drawable?.toBitmap()?.asImageBitmap()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (bitmap != null) {
            Canvas(
                modifier = Modifier.size(150.dp) // Increase the size as needed
            ) {
                val iconWidth = size.width * animatable.value
                val centerX = (size.width - bitmap.width) / 2f
                val centerY = (size.height - bitmap.height) / 2f
                clipRect(left = 0f, top = 0f, right = iconWidth, bottom = size.height) {
                    drawImage(bitmap, topLeft = Offset(centerX, centerY))
                }
            }
        }
    }
}

@Composable
fun PlayWavSound() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer()
        try {
            val afd =
                context.resources.openRawResourceFd(R.raw.wind_sound) // Replace 'wind' with your file's name (without extension)
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()

            mediaPlayer.setOnPreparedListener {
                it.start()
            }
            mediaPlayer.prepareAsync()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        onDispose {
            mediaPlayer.release()
        }
    }
}

enum class ItemState {
    SELECTED,
    UNSELECTED
}

fun ItemState.onItemClickState() =
    if (this == ItemState.SELECTED) {
        ItemState.UNSELECTED
    } else {
        ItemState.SELECTED
    }

fun <T : GeneralItem> List<GeneralItem>.isSelected(t: T) =
    if (this.contains(t)) {
        ItemState.SELECTED
    } else {
        ItemState.UNSELECTED
    }


fun selectPlate(type: EMealType = EMealType.BREAKFAST): Int =
    when (type) {
        EMealType.BRUNCH -> R.drawable.ic_blue_plate
        EMealType.LUNCH_DINNER -> R.drawable.ic_cast_iron
        else -> R.drawable.ic_plate
    }

fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)
}