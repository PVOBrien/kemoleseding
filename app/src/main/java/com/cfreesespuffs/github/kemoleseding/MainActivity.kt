package com.cfreesespuffs.github.kemoleseding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.cfreesespuffs.github.kemoleseding.objModules.Module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolbarWidget()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun KemoLesedingTheme() {
    // A surface container using the 'background' color from the theme
    Surface(
        color = kmlLightBlue,
        modifier = Modifier.fillMaxSize(),
    ) {
        val modList = listOf<Module>(modOne, modTwo, modThree, modFour)
        Text(
            text = "Click each for additional details",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            // https://foso.github.io/Jetpack-Compose-Playground/foundation/lazycolumn/
            modifier = Modifier
                .padding(top = 24.dp, start = 10.dp, bottom = 18.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            // todo: think about adding a top and bottom fade
        ) {
            itemsIndexed(modList) { _, item -> // https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#lazycolumn
                MCard(item.title, item.summary, item.modPhoto)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MCard(mCString: String, summary: String, picInt: Int) {
    var expanded by remember { mutableStateOf(false) }
// https://joebirch.co/android/exploring-jetpack-compose-card/
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
        Column(modifier = Modifier.padding(bottom = 3.dp)) {
            Text( // this is the Title
                text = mCString,
                Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp)
                    .offset(y = (-4).dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 4.em,
                fontWeight = FontWeight.Bold,
                color = Color.White // otherwise button text defaults to Black
            )
            ModuleCardBody(summary, picInt, expanded)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ModuleCardBody(mSummary: String, picInt: Int, isExpanded: Boolean) {
    Row(
        modifier = Modifier
            .wrapContentSize()
    ) {
        MPic(picInt, isExpanded)
        ModuleDetails(mSummary, isExpanded)
    }
}

@ExperimentalAnimationApi
@Composable
fun MPic(picInt: Int, isExpanded: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) { // https://stackoverflow.com/questions/60479567/how-to-center-elements-inside-a-column-in-jetpack-compose
        Image(
            painter = painterResource(id = picInt),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = "Stanley walking strong"
        )
        AnimatedVisibility (isExpanded) {
            Image(
                painter = painterResource(id = R.drawable.doc_pic),
                contentDescription = "Document Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(48.dp)
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter,
            )
        }

    }
}

@Composable
fun ModuleDetails(theSummary: String, isExpanded: Boolean) {
    Column(
        Modifier
            .padding(horizontal = Dp(6.0F))
            .background(kmlRed)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            ) // https://www.youtube.com/watch?v=7yY2OocGiQU&t=243s and https://developer.android.com/jetpack/compose/animation#animatedcontent to modify the specs
    ) {
        Text(
            text = theSummary,
            maxLines = if (isExpanded) Int.MAX_VALUE else 6,
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

@OptIn(ExperimentalAnimationApi::class)
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