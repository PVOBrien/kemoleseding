package com.kml.github.kemoleseding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens (val route: String, val title: String) {

    sealed class TopScreens(
        route: String,
        title: String,
        val icon: ImageVector
    ) : Screens(
        route,
        title
    ) {
        object SplashScreen : TopScreens("SplashScreen", "Splash", Icons.Filled.Notifications)
        object Home : TopScreens("KemoLesedingTheme", "Home", Icons.Filled.Star)
        object Curriculum : TopScreens("curriculum", "Curriculum", Icons.Filled.Info)
        object About : TopScreens("About", "About", Icons.Filled.Info)
    }
}

val screensFromDrawer = listOf(
    Screens.TopScreens.Home,
    Screens.TopScreens.Curriculum,
    Screens.TopScreens.About,
)