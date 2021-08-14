package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cfreesespuffs.github.kemoleseding.objModules.*
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed

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
                .padding(horizontal = 12.dp, vertical = 18.dp),
            Arrangement.spacedBy(18.dp)
        ) {
            MCard(modOne.title, modOne.summary)
            MCard(modTwo.title, modTwo.summary)
            MCard(modThree.title, modThree.summary)
            MCard(modFour.title, modFour.summary)
        }
    }
}

@Composable
fun MCard(mCString: String, summary: String) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = kmlRed,
//        contentColor = Color.White,
//        border = BorderStroke(2.dp, Color.Black),
//        modifier = Modifier.shadow(
//            5.dp,
//            RoundedCornerShape(16.dp)
//        ) // shadows only show in emulator, not DefaultPreview. at least at this Android SDK level (23?)
//    ) {
    var expanded by remember { mutableStateOf(false) }
    Button(
        // to be used to show/animate https://developer.android.com/jetpack/compose/animation
        onClick = { expanded = !expanded },
        colors = ButtonDefaults.buttonColors(backgroundColor = kmlRed), // https://stackoverflow.com/questions/64376333/background-color-on-button-in-jetpack-compose
        shape = RoundedCornerShape(16.dp), // doesn't seem to inherit the exact shape from above.
        modifier = Modifier.shadow(
            5.dp,
            RoundedCornerShape(16.dp)
        )
    ) {
        Column(modifier = Modifier.padding(0.dp)) {
            Text( // this is the Title
                text = mCString,
                Modifier
                    .fillMaxWidth(),
                fontFamily = FontFamily.SansSerif,
                fontSize = 4.em,
//                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White // otherwise button text defaults to Black
            )
            ModuleCardBody(summary)
        }
    }
}
//}

@Composable
fun ModuleCardBody(mSummary: String) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 3.dp, start = 4.dp, end = 4.dp)
    ) {
        MPic()
        ModuleDetails(mSummary)
    }
}

@Composable
fun MPic() {
    Image(
        painter = painterResource(id = R.drawable.module_one_pic),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentDescription = "Stanley walking strong"
    )
}

@Composable
fun ModuleDetails(theSummary: String) {
    Column(
        Modifier
            .padding(horizontal = Dp(6.0F))
            .background(kmlRed),
    ) {
        Text(
            text = theSummary,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis, // https://developer.android.com/jetpack/compose/text
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 3.em,
            modifier = Modifier.background(kmlRed)
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
                elevation = 6.dp
            )
        }, content = {
            KemoLesedingTheme()
        })
}


// "Trash" below