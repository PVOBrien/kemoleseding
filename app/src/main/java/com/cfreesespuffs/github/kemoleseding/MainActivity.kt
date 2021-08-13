package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolbarWidget()
        }
    }
}

@Composable
fun KemoLesedingTheme() {
    // A surface container using the 'background' color from the theme
    Surface(
        color = kmlLightBlue,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(18.dp)),
            Arrangement.spacedBy(36.dp)
        ) {
            MCard("the biggestbaddest")
            androidx.compose.material.Card(
                elevation = 30.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = kmlRed,
                contentColor = Color.White,
                border = BorderStroke(3.dp, Color.Black),
            ) {
                Module("1")
            }
            androidx.compose.material.Card(
                elevation = 30.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = kmlRed,
                contentColor = Color.White,
                border = BorderStroke(3.dp, Color.Black),
            ) {
                Module("The New TitAL")
            }
            androidx.compose.material.Card(
                elevation = 30.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = kmlRed,
                contentColor = Color.White,
                border = BorderStroke(3.dp, Color.Black),
            ) {
                Module("Diff")
            }
        }
        }
//        ) {
//            Greeting("Android")
//        Module()
//    }
    }


@Composable
fun Module(mString: String) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        MPic()
        ModuleDetails(mString)
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
fun MCard(mCString: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = kmlRed,
        contentColor = Color.White,
        border = BorderStroke(3.dp, Color.Black),
    ) {
        Module(mCString)
    }
}

@Composable
fun ModuleDetails(theTitle: String) {
    Column(Modifier.padding(horizontal = Dp(6.0F))) {
        Text(
            text = theTitle,
            fontFamily = FontFamily.Cursive,
            fontSize = 6.em,
            fontWeight = FontWeight.Bold,
            color = Color.White,
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
                        text = "Ke mo Leseding Title",
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

// "Trash" below

@Composable
fun Greeting(name: String) {
    Text(
        text = "Really!? $name!",
        fontSize = 23.sp,
        color = kmlYellow
    )
}