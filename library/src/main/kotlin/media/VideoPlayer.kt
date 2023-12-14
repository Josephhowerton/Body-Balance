package media

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay
@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(video: Int) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val uri = RawResourceDataSource.buildRawResourceUri(video)
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    val fadeOutDurationMillis = 2000L
    val fadeOutStartMillis = exoPlayer.duration - fadeOutDurationMillis

    val fadingOut = remember { mutableStateOf(false) }

    LaunchedEffect(exoPlayer) {
        while (true) {
            val currentPosition = exoPlayer.currentPosition
            Log.e("VideoView", "Current Position: $currentPosition, Duration: ${exoPlayer.duration}")

            if (currentPosition >= fadeOutStartMillis && !fadingOut.value) {
                Log.e("VideoView", "Fading Out")
                fadingOut.value = true
            }

            if (fadingOut.value && currentPosition <= 500) {
                Log.e("VideoView", "Resetting")
                exoPlayer.seekTo(0)
                fadingOut.value = false
            }

            delay(500)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    useController = false
                }
            }
        )

        if (fadingOut.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Adjust the alpha as needed
            ) {
                // You can add additional UI elements or animations here during the fade-out
            }
        }
    }
}
