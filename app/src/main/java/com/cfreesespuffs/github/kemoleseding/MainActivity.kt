package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnitType.Companion.Em
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolbarWidget()
//            KemoLesedingTheme()
        }
    }
}

@Composable
fun KemoLesedingTheme() {
    // A surface container using the 'background' color from the theme
    Surface(
        color = kmlLightBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 6.dp)
        ) {
            Card(elevation = 18.dp) {
                Module()
            }
        }
//        ) {
//            Greeting("Android")
//        Module()
//    }
    }
}

@Composable
fun Module() {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(kmlRed) // no longer in the xml, look to the ui.theme package
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        MPic()
        ModuleDetails()
    }
}

@Composable
fun MPic() {
    Image(
        painter = painterResource(id = R.drawable.module_one_pic),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentDescription = "Stanley walking strong"
    )
}

@Composable
fun ModuleDetails() {
    Column(Modifier.padding(horizontal = Dp(6.0F))) {
        Text(
            text = "Title",
            fontFamily = FontFamily.Cursive,
            fontSize = 6.em,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Summary of the film and it goes for this long until some space ru",
            Modifier.padding(horizontal = Dp(0.0F), vertical = Dp(8.0F)),
            color = Color.White
        )
    }
}

// **== PREVIEW CALL ==**

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToolbarWidget()
//    KemoLesedingTheme { // order matters, runs top to bottom.
//        Greeting("Android")
//    }
}

// "Trash" below

@Composable
fun Greeting(name: String) {
    Text(
        text = "Really!? $name!",
        fontSize = 23.sp,
        color = kmlYellow
    )
}

@Composable
fun ToolbarWidget() {
    // theme for our app.
    Scaffold(
        // below line we are
        // creating a top bar.
        topBar = {
            TopAppBar(
                // in below line we are
                // adding title to our top bar.
                title = {
                    // inside title we are
                    // adding text to our toolbar.
                    Text(
                        text = "Ke mo Leseding",
                        // below line is use
                        // to give text color.
                        color = Color.White
                    )
                },
                navigationIcon = {
                    // navigation icon is use
                    // for drawer icon.
                    IconButton(onClick = { }) {
                        // below line is use to
                        // specify navigation icon.
//                        Icon(Icons.Filled.Menu)
                    }
                },
                // below line is use to give background color
                backgroundColor = kmlRed,

                // content color is use to give
                // color to our content in our toolbar.
                contentColor = Color.White,

                // below line is use to give
                // elevation to our toolbar.
                elevation = 12.dp
            )
        }, content = {
            KemoLesedingTheme()
        })
}