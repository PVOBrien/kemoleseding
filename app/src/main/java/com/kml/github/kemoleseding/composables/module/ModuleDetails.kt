package com.kml.github.kemoleseding.composables.module

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import com.kml.github.kemoleseding.ui.theme.kmlRed

@Composable
fun ModuleDetails(theSummary: String, isExpanded: Boolean) {
    Column(
        Modifier
            .padding(horizontal = Dp(6.0F))
            .background(kmlLightBlue)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            )
    ) {
        Text(
            text = theSummary,
            maxLines = if (isExpanded) Int.MAX_VALUE else 6,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 3.em,
            modifier = Modifier.background(kmlLightBlue)
        )
    }
}