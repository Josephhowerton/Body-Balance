package com.fitness.component.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun odyBalanceImageLogoPreview() {
    BodyBalanceTheme {
        Surface {
            BodyBalanceImageLogo(modifier = Modifier)
        }
    }
}

@Composable
fun BodyBalanceImageLogo(contentScale: ContentScale = ContentScale.Fit, modifier: Modifier){
    Image(
        modifier = modifier,
        contentScale = contentScale,
        painter = painterResource(id = R.drawable.cloud_no_shadow),
        contentDescription = stringResource(id = R.string.content_description_training),
    )
}