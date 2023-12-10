package extensions

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class Dark

@Preview(name = "Light", showBackground = true)
annotation class Light