package com.cfreesespuffs.github.kemoleseding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
        object Home : TopScreens("home", "Main Screen", Icons.Filled.Star)
        object Curriculum : TopScreens("curriculum", "Curriculum", Icons.Filled.Info)
        object About : TopScreens("about", "About", Icons.Filled.Info)
    }
}

val screensFromDrawer = listOf(
    Screens.TopScreens.Home,
    Screens.TopScreens.Curriculum,
    Screens.TopScreens.About
)