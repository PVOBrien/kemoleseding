package com.kml.github.kemoleseding.composables

import android.content.SharedPreferences
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kml.github.kemoleseding.*
import com.kml.github.kemoleseding.hiltContent.HiltViewModelThis
import com.kml.github.kemoleseding.ui.theme.kmlRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun KmLApp(sPs: SharedPreferences) {

    val testVM: HiltViewModelThis = hiltViewModel() // https://stackoverflow.com/questions/70457125/how-to-inject-a-singleton-using-hilt-inside-a-composable#:~:text=You%20cannot%20inject%20dependencies%20into%20a%20function%2C%20which,values%20returned%20by%20Hilt%20functions%2C%20like%20hiltViewModel%20%28%29

//    @Inject
//    lateinit var hiltClasses: HiltClasses

    var language = sPs.getBoolean("language", false)

    val viewModel: MainViewModel = viewModel()
    viewModel.isSetswana = language

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.observeAsState()
    var openMod by rememberSaveable { mutableStateOf(false) }
    var topWhichLang by remember { mutableStateOf(viewModel.isSetswana) }

    @Composable
    fun topBar(title: String = "") {
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
                topBar(currentScreen!!.title)
            }
        },
        drawerContent = {
            MDrawerContent(viewModel, topWhichLang, { topWhichLang = !topWhichLang }) { route ->
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
        drawerShape =
        object : Shape {
            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                return Outline.Rectangle(Rect(left = 0f, top = 0f, right = size.width * 2 / 3, bottom = size.height))
            }
        },
        content = {

            NavHost(
                navController = navController,
                startDestination = "SplashScreen"
            )
            {
                composable(Screens.TopScreens.SplashScreen.route) { SplashScreen(navController = navController) }
                composable(Screens.TopScreens.Home.route) { KemoLesedingTheme(viewModel = viewModel, modDown = { openMod = !openMod }, openMod, topWhichLang) }
                composable(Screens.TopScreens.Curriculum.route) { Curriculum(viewModel = viewModel) }
                composable(Screens.TopScreens.About.route) { About(viewModel = viewModel, topWhichLang) }
            }
        }
    )
}