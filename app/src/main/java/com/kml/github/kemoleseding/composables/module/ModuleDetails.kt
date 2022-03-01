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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kml.github.kemoleseding.composables.AppTheme
import com.kml.github.kemoleseding.ui.theme.Typography
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue

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
            style = Typography.body2,
            fontSize = AppTheme.dimens.typeSizeContent,
//            fontSize = if (LocalConfiguration.current.screenWidthDp <= 400) 45.sp else 50.sp,
            modifier = Modifier.background(kmlLightBlue)
        )
        println("Screen Width: " + LocalConfiguration.current.screenWidthDp)
        println("dimen: " + AppTheme.dimens.typeSizeContent.value)

    }
}