package com.fitness.theme.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.fitness.resources.R

private val fredoka = FontFamily(
    Font(R.font.fredoka_regular, weight = FontWeight.Normal),
    Font(R.font.fredoka_bold, weight = FontWeight.Bold),
    Font(R.font.fredoka_light, weight = FontWeight.Light),
    Font(R.font.fredoka_light, weight = FontWeight.ExtraLight),
    Font(R.font.fredoka_medium, weight = FontWeight.Medium),
    Font(R.font.fredoka_light, weight = FontWeight.Thin),
    Font(R.font.fredoka_semi_bold, weight = FontWeight.SemiBold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fredoka,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)