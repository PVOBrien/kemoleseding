package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cfreesespuffs.github.kemoleseding.composables.About
import com.cfreesespuffs.github.kemoleseding.composables.KemoLesedingTheme
import com.cfreesespuffs.github.kemoleseding.composables.MDrawerContent
import com.cfreesespuffs.github.kemoleseding.objModules.*
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
    val scope =
        rememberCoroutineScope() // this is same as messages to reach the main thread. not *the* routine, but a COroutine.
    val currentScreen by viewModel.currentScreen.observeAsState() // https://stackoverflow.com/questions/66560404/jetpack-compose-unresolved-reference-observeasstate

    @Composable
    fun topBar(title: String = "", buttonIcon: ImageVector) { // , onButtonClicked: () -> Unit
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
            topBar(currentScreen!!.title, buttonIcon = Icons.Filled.Menu)
        },
        drawerContent = { // https://joebirch.co/android/exploring-jetpack-compose-column/#:~:text=Exploring%20Jetpack%20Compose%3A%20Column%201%20Declaring%20Children.%20When,inside%20of%20the%20Column.%204%20Weight%20Modifier.%20
            MDrawerContent() { route ->
                println("this is $route")
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    popUpTo(route)
                    launchSingleTop = true
                }
            }
        },
        content = {
            var fileShow by remember { mutableStateOf(false) }
            val modList = listOf(modOne, modTwo, modThree, modFour)
            var whichMod by remember { mutableStateOf(0) }

            NavHost(
                navController = navController as NavHostController,
                startDestination = "KemoLesedingTheme"
            )
            {
                composable("Home") { Home() }
                composable(Screens.TopScreens.Home.route) {
                    KemoLesedingTheme(viewModel = viewModel,
                    onWhichModChange = { whichMod = it },
                    onFileShowChange = { fileShow = !fileShow }
                    )
                }
                composable(Screens.TopScreens.Curriculum.route) { Home() }
                composable(Screens.TopScreens.About.route) { About(viewModel = viewModel) }
            }


//            KemoLesedingTheme(
//                modList,
//                fileShow,
//                onFileShowChange = { fileShow = !fileShow },
//                viewModel = MainViewModel()
//            ) { whichMod = it }
//            DocAbove(
//                fileShow,
//                onFileShowChange = { fileShow = !fileShow },
//                modList[whichMod].docList
//            )
        }
    )
}

@Composable
fun Home() { // viewModel: MainViewModel
//    viewModel.setCurrentScreen(Screens.TopScreens.Home)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home", style = MaterialTheme.typography.h4)
    }
}

//@ExperimentalAnimationApi
//@Composable
//fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
//    NavHost(
//        navController = navController as NavHostController,
//        startDestination = "Home"
//    )
//    {
//        composable("Home") { Home() }
////        composable(Screens.TopScreens.Home.route) { KemoLesedingTheme(viewModel = viewModel) }
//        composable(Screens.TopScreens.Curriculum.route) { Home() }
//        composable(Screens.TopScreens.About.route) { About(viewModel = viewModel) }
//    }
//}

// **== PREVIEW CALL ==**

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KmLApp()
}