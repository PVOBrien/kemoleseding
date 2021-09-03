package com.cfreesespuffs.github.kemoleseding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.core.content.FileProvider
import com.cfreesespuffs.github.kemoleseding.objModules.*
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow
import java.io.*

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
fun KemoLesedingTheme(
    modlist: List<Module>,
    fileShow: Boolean,
    cardFile: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {
    // A surface container using the 'background' color from the theme
    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClick = {
                    println("onSurface")
                    onFileShowChange(!fileShow)
                }
            )
    ) {

//        val modList = listOf(modOne, modTwo, modThree, modFour)
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
            itemsIndexed(modlist) { itemCount, item -> // https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#lazycolumn
                MCard(
                    item.title,
                    item.summary,
                    item.modPhoto,
                    itemCount, // itemCount is the index position of *the* item.
                    fileShow,
                    cardFile,
                    onFileShowChange,
                    onWhichModChange
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MCard(
    mCString: String,
    summary: String,
    picInt: Int,
    cardCount: Int,
    fileShow: Boolean,
    cardFile: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) { // https://joebirch.co/android/exploring-jetpack-compose-card/
    println("the cardCount: $cardCount")
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
            ModuleCardBody(
                summary,
                picInt,
                expanded,
                cardCount,
                fileShow,
                cardFile,
                onFileShowChange,
                onWhichModChange
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ModuleCardBody(
    mSummary: String,
    picInt: Int,
    isExpanded: Boolean,
    cardCount: Int,
    fileShow: Boolean,
    cardFile: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
    ) {
        MPic(picInt, isExpanded, cardCount, fileShow, cardFile, onFileShowChange, onWhichModChange)
        ModuleDetails(mSummary, isExpanded)
    }
}

@ExperimentalAnimationApi
@Composable
fun MPic(
    picInt: Int,
    isExpanded: Boolean,
    ofCard: Int,
    fileShow: Boolean,
    cardFile: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {

    var docsExpanded by remember { mutableStateOf(false) }

    var numbersList: List<Int> = listOf(1, 2, 3, 4)

    val context =
        LocalContext.current // https://stackoverflow.com/questions/64994507/is-there-a-way-to-open-a-webpage-on-click-of-iconbutton-from-the-topappbar-in-a
    Column(horizontalAlignment = Alignment.CenterHorizontally) { // https://stackoverflow.com/questions/60479567/how-to-center-elements-inside-a-column-in-jetpack-compose

        Image(
            painter = painterResource(id = picInt),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = "Stanley walking strong"
        )

        AnimatedVisibility(isExpanded) {
            Image(
//                painter = painterResource(id = R.drawable.doc_pic),
                painter = painterResource(id = R.drawable.folder),
                contentDescription = "Document Icon",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(48.dp)
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClickLabel = "This is a Document",
                        onClick = {
//                            fileShow = !fileShow
                            var quickBool: Boolean = false
                            onFileShowChange(!quickBool)
                            println("CLICK PIC")
                            onWhichModChange(ofCard)
                        })
//                        onClick = { openFile(context) }) // TODO: pass addtl variables for which files.
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun docAbove(
    isVisible: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    passedDocDetails: List<docDetails>,
//    modList: List<Module>,
//    whichMod: Int
) {
    val context = LocalContext.current
    var numbersList: List<Int> = listOf(1, 2, 3, 4)
    println("this is the current docDetail file path: ${passedDocDetails?.get(0)?.docName}")
    AnimatedVisibility(
        isVisible,
        enter = slideInVertically(),
        exit = slideOutVertically(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), // THIS CENTERS STUFF.
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = kmlYellow,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                LazyRow(
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Show docs or not",
                            onClick = {
                                onFileShowChange(!isVisible)
                            }
                        )
                        .background(color = kmlYellow, shape = RoundedCornerShape(8.dp)),
                )
                {
                    val allData = passedDocDetails.toList() // https://stackoverflow.com/questions/46846025/how-to-clone-or-copy-a-list-in-kotlin
                    items(passedDocDetails) { item ->
                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(item.picType),
                                contentDescription = "Document Icon",
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 6.dp)
                                    .size(40.dp)
                                    .clickable(
                                        enabled = true,
                                        onClickLabel = "This is a Document",
                                        onClick = {
//                                            var quickBool: Boolean = false
//                                            onFileShowChange(!quickBool)
                                            println("CLICK PIC" + "${item.docName}")
                                            openFile(context, item.docName)
                                        })
//                        onClick = { openFile(context) }) // TODO: pass addtl variables for which files.
                            )
                            Text(
                                text = "${item.docDescription}",
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    bottom = 8.dp
                                )
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier.padding(all = 12.dp),
                    onClick = { onFileShowChange(!isVisible) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = kmlLightBlue), // https://stackoverflow.com/questions/64376333/background-color-on-button-in-jetpack-compose
                )
                {
                    Text(text = "Close")
                }
            }
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
            var fileShow by remember { mutableStateOf(false) }
            var cardFile by remember { mutableStateOf(0) }
            val modList = listOf(modOne, modTwo, modThree, modFour)
            var whichMod by remember { mutableStateOf(0) }

            KemoLesedingTheme(
                modList,
                fileShow,
                cardFile,
                onFileShowChange = { fileShow = !fileShow },
                onWhichModChange = { whichMod = it })
            docAbove(
                fileShow,
                onFileShowChange = { fileShow = !fileShow },
                modList[whichMod].docList
            )
        })
}

private fun openFile(
    theContext: Context,
    theFile: String
) { // TODO: add addtl variables for which files.
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null

//    val theFile = "daysfriends"

    try {
        val file =
            File("${theContext.getExternalFilesDir("kmlpdfFromFile")}" + "/$theFile.pdf") // "kmlpdfFromFile"
        if (!file.exists()) {
            println("in file doesn't exist block")
          inputStream = theContext.assets.open("pdfs/$theFile.pdf")
//            inputStream = theContext.resources.openRawResource(R.raw.daysfriends)
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)
        }

        val uri = FileProvider.getUriForFile(
            theContext,
            "com.cfreesespuffs.github.kemoleseding.provider",
            file
        )

        println(uri.toString())
        val pdfToOpen = Intent(Intent.ACTION_VIEW)
            .setDataAndType(
                uri,
                theContext.contentResolver.getType(uri)
            ) // context.contentResolver.getType(<file>) is the best way to get the MIME type for any file, as it requires no work on coder's part
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)

        val intent = Intent.createChooser(
            pdfToOpen,
            "Open Pdf using..."
        )
        theContext.startActivity(intent)

    } catch (ex: IOException) {
        println("IOException " + ex.message)
    } catch (ex: ActivityNotFoundException) {
        println(ex.message)
    } finally {
        inputStream?.close()
        outputStream?.flush()
        outputStream?.close()
    }
    println("File copy and/or view in action")
}

private fun copyFile(input: InputStream, output: OutputStream) {
    println("in copyfile")
    val buffer = ByteArray(1024)
    var read: Int = input.read(buffer)
    while (read != -1) {
        output.write(buffer, 0, read)
        read = input.read(buffer)
        println(buffer)
    }
}
