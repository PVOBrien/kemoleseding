package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.cfreesespuffs.github.kemoleseding.composables.KemoLesedingTheme
import com.cfreesespuffs.github.kemoleseding.composables.MDrawerContent
import com.cfreesespuffs.github.kemoleseding.objModules.modFour
import com.cfreesespuffs.github.kemoleseding.objModules.modOne
import com.cfreesespuffs.github.kemoleseding.objModules.modThree
import com.cfreesespuffs.github.kemoleseding.objModules.modTwo
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolbarWidget()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToolbarWidget() {

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
        content = {
            var fileShow by remember { mutableStateOf(false) }
            val modList = listOf(modOne, modTwo, modThree, modFour)
            var whichMod by remember { mutableStateOf(0) }

            KemoLesedingTheme(
                modList,
                fileShow,
                onFileShowChange = { fileShow = !fileShow },
                viewModel = MainViewModel()
            ) { whichMod = it }
            DocAbove(
                fileShow,
                onFileShowChange = { fileShow = !fileShow },
                modList[whichMod].docList
            )
        },
        drawerContent = { // https://joebirch.co/android/exploring-jetpack-compose-column/#:~:text=Exploring%20Jetpack%20Compose%3A%20Column%201%20Declaring%20Children.%20When,inside%20of%20the%20Column.%204%20Weight%20Modifier.%20
            MDrawerContent()
        }
    )
}



// **== PREVIEW CALL ==**

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToolbarWidget()
}