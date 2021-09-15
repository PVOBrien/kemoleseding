package com.cfreesespuffs.github.kemoleseding

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
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
            topBar(currentScreen!!.title, buttonIcon = Icons.Filled.Menu)
        },
        drawerContent = {
            MDrawerContent() { route ->
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
                startDestination = "KemoLesedingTheme"
            )
            {
                composable(Screens.TopScreens.Home.route) {
                    KemoLesedingTheme(
                        viewModel = viewModel,
                    )
                }
                composable(Screens.TopScreens.Curriculum.route) { Curriculum(viewModel = viewModel) }
                composable(Screens.TopScreens.About.route) { About(viewModel = viewModel) }
            }
        }
    )
}

@Composable
fun Curriculum(viewModel: MainViewModel) {

    val context = LocalContext.current

    viewModel.setCurrentScreen(Screens.TopScreens.Curriculum)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)) {
                    append("Ke Mo Leseding")
                }
                append(" is a full HIV and AIDS awareness package, complete with training materials, videos, and a facilitator guide. Reach out to Thusang Bana Center for information to receive additional information.")
            },
//            text = "Ke mo Leseding is a full HIV and AIDS awareness package, complete with training materials, videos, and a facilitator guide. Reach out to Thusang Bana Center for information to receive additional information.",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(12.dp)
        )
        Row() {
            Image(
                painterResource(id = R.drawable.kmlcurriculum),
                contentDescription = "Ke mo Leseding Curriculum",
                modifier = Modifier
                    .size(196.dp)
            )
            Image(
                painterResource(id = R.drawable.kmlleaderguide),
                contentDescription = "KmL Leader's Guide",
                modifier = Modifier
                    .size(196.dp)
            )
        }
        Button(onClick = {
            println("hello email!")
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, "thusangbanacenter@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Ke mo Leseding Content Request")
            startActivity(context, Intent.createChooser(intent, "Send Email..."), null)
        }) {
            Text(text = "Email Thusang Bana Center")
        }
    }
}

// **== PREVIEW CALL ==**

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KmLApp()
}