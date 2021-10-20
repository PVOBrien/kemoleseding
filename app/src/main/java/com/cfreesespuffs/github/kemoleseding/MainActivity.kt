package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cfreesespuffs.github.kemoleseding.composables.About
import com.cfreesespuffs.github.kemoleseding.composables.Curriculum
import com.cfreesespuffs.github.kemoleseding.composables.KemoLesedingTheme
import com.cfreesespuffs.github.kemoleseding.composables.MDrawerContent
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KmLApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun KmLApp() {

    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.observeAsState()

    @Composable
    fun topBar(title: String = "", buttonIcon: ImageVector) {
        TopAppBar(
            title = {
                Text(title, color = Color.White)
            },
            navigationIcon = {
                IconButton(
                    enabled = true,
                    onClick = {
                        println("melon!")
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = ""
                    )
                }
            },
            backgroundColor = kmlRed,
            contentColor = Color.White,
            elevation = 6.dp
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (!currentScreen?.route.equals("SplashScreen")) {
                topBar(currentScreen!!.title, buttonIcon = Icons.Filled.Menu)
            }
        },
        drawerContent = {
            MDrawerContent(viewModel = viewModel) { route ->
                println("this is $route")
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    popUpTo("KemoLesedingTheme")
                    launchSingleTop = true
                }
            }
        },
        content = {

            NavHost(
                navController = navController,
                startDestination = "SplashScreen"
            )
            {
                composable(Screens.TopScreens.SplashScreen.route) { SplashScreen(navController = navController) }
                composable(Screens.TopScreens.Home.route) { KemoLesedingTheme(viewModel = viewModel) }
                composable(Screens.TopScreens.Curriculum.route) { Curriculum(viewModel = viewModel) }
                composable(Screens.TopScreens.About.route) { About(viewModel = viewModel) }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KmLApp()
}