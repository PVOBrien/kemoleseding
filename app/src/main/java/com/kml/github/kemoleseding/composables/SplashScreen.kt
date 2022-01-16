package com.kml.github.kemoleseding.composables

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.kml.github.kemoleseding.R
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember { androidx.compose.animation.core.Animatable(0f) }

    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
    ) {

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = .7F,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    }
                )
            )
            delay(1200L)
            scale.animateTo(
                targetValue = .0F,
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearOutSlowInEasing
                )
            )
            delay(100L)
            navController.popBackStack()
            navController.navigate("KemoLesedingTheme")
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.kmlmark),
                contentDescription = "Ke mo Leseding Mark",
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}