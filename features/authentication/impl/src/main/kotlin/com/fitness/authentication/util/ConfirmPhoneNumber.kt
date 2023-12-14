package com.fitness.authentication.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardButton
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTextField
import com.fitness.component.components.StandardTextSmall
import com.fitness.component.properties.GuidelineProperties
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConfirmationCodePreview() {
    BodyBalanceTheme {
        Surface {
            ConfirmationCodeScreen()
        }
    }
}

@Composable
fun ConfirmationCodeScreen(onClickContinue: () -> Unit = {}) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            welcome,
            message,
            phone,
            signUp,
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(GuidelineProperties.TOP)
        val startGuideline = createGuidelineFromStart(GuidelineProperties.START)
        val endGuideline = createGuidelineFromEnd(GuidelineProperties.END)

        var userCode by remember { mutableStateOf("") }

        StandardText(
            text = R.string.welcome_to_body_balance,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(welcome) {
                start.linkTo(startGuideline)
                top.linkTo(topGuideline)
                bottom.linkTo(message.top)
            }
        )

        StandardTextSmall(
            text = R.string.in_a_moment_you_receive_a_cod,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(message) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(welcome.bottom, 10.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardTextField(
            value = userCode,
            hint = R.string.enter_code,
            label = R.string.label_code,
            onValueChanged = { userCode = it },
            modifier = Modifier.constrainAs(phone) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(message.bottom, 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        StandardButton(
            text = R.string.title_continue,
            onClick = onClickContinue,
            modifier = Modifier.constrainAs(signUp) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                top.linkTo(phone.bottom, 25.dp)
                width = Dimension.fillToConstraints
            }
        )
    }
}