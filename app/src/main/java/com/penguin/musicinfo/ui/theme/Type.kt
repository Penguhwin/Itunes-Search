package com.penguin.musicinfo.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val ContentPaddingLarge = 28.dp
val ContentPaddingMedium = 10.dp
val ContentPaddingSmall = 8.dp
val ContentPaddingTiny = 4.dp

data class Specs(
    val padding: Dp = ContentPaddingMedium,
    val paddingSmall: Dp = ContentPaddingSmall,
    val paddingTiny: Dp = ContentPaddingTiny,
    val paddingLarge: Dp = ContentPaddingLarge,

    val paddings: PaddingValues = PaddingValues(padding),
    val inputPaddings: PaddingValues = PaddingValues(horizontal = padding, vertical = paddingSmall),

    val iconSize: Dp = 36.dp,
    val iconSizeSmall: Dp = 28.dp,
    val iconSizeTiny: Dp = 18.dp,
    val iconSizeLarge: Dp = 48.dp,
)

val DefaultSpecs = Specs()
