package com.fitness.onboard.onboard.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.fitness.component.components.StandardHeadlineText
import com.fitness.component.components.StandardText
import com.fitness.component.components.StandardTitleText
import com.fitness.component.properties.GuidelineProperties
import com.fitness.resources.R
import com.fitness.theme.ui.BodyBalanceTheme

@Preview
@Composable
fun PreviewVerificationCodeInput() = BodyBalanceTheme {
    Surface {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (title, description, features, getStarted) = createRefs()

        val titleTop = createGuidelineFromTop(GuidelineProperties.SECOND_TOP)
        val titleBottom = createGuidelineFromTop(275.dp)
        val startGuide = createGuidelineFromStart(GuidelineProperties.START)
        val endGuide = createGuidelineFromEnd(GuidelineProperties.END)
        val bottomGuide = createGuidelineFromBottom(GuidelineProperties.BOTTOM_100)

        
        StandardTitleText(
            fontSize = 36.sp,
            text = R.string.app_name,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(titleTop)
                bottom.linkTo(titleBottom)
                start.linkTo(startGuide)
            }
        )

        StandardHeadlineText(
            text = R.string.onboarding_welcome_description,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(title.bottom, 20.dp)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

        FeatureList(modifier = Modifier.constrainAs(features) {
            top.linkTo(description.bottom, 20.dp)
            bottom.linkTo(getStarted.top)
            start.linkTo(startGuide)
            height = Dimension.fillToConstraints
        })

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(getStarted) {
                end.linkTo(endGuide)
                bottom.linkTo(bottomGuide)
            }
        ) {
            Text(text = stringResource(id = R.string.title_continue))
        }
    }
}

@Composable
fun FeatureList(modifier: Modifier){
    Column(modifier=modifier) {

        Spacer(modifier = Modifier.size(50.dp))

        StandardText(
            text = R.string.feature_planning,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.size(20.dp))

        StandardText(
            text = R.string.feature_tracking,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.size(20.dp))

        StandardText(
            text = R.string.feature_deep_analysis,
            textAlign = TextAlign.Start,
        )
    }
}
