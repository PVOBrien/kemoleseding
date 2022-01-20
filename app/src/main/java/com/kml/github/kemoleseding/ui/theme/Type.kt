package com.kml.github.kemoleseding.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kml.github.kemoleseding.R

val Abys = FontFamily(
    Font(R.font.abysreg),
)

val NotoSerif = FontFamily(
    Font(R.font.notoserif)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = Abys,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    body2 = TextStyle(
        fontFamily = NotoSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)