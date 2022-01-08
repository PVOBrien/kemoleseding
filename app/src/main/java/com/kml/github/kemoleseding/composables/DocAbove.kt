package com.kml.github.kemoleseding.composables

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kml.github.kemoleseding.R
import com.kml.github.kemoleseding.Video
import com.kml.github.kemoleseding.objModules.DocDetails
import com.kml.github.kemoleseding.openFile
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import com.kml.github.kemoleseding.ui.theme.kmlYellow

@ExperimentalAnimationApi
@Composable
fun DocAbove(
    isVisible: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    passedDocDetails: List<DocDetails>,
) {
    val context = LocalContext.current
    AnimatedVisibility(
        isVisible,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
                    items(passedDocDetails) { item ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(item.picType),
                                contentDescription = "Document Icon",
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 12.dp, end = 12.dp)
                                    .size(40.dp)
                                    .clickable(
                                        enabled = true,
                                        onClickLabel = "This is a Document",
                                        onClick = {
                                            println("CLICK PIC ${item.docName}")
                                            if (item.picType == R.drawable.ic_baseline_play_circle_filled_24) {
                                                println("play video ${item.docName}")
                                                val intent = Intent(context, Video::class.java)
//                                                intent.putExtra("video", Ur)
                                                context.startActivity(intent)
                                            } else {
                                                openFile(context, item.docName)
                                            }
                                        }
                                    )
                            )
                            Text(
                                text = item.docDescription,
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                                    .clickable(
                                        enabled = true,
                                        onClickLabel = "Show the file",
                                        onClick = {
                                            println("ClICK TEXT ${item.docName}")
                                            if (item.picType == R.drawable.ic_baseline_play_circle_filled_24) {
                                                println("play video ${item.docName}")
                                            } else {
                                                openFile(context, item.docName)
                                            }
                                        }
                                    ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier.padding(all = 12.dp),
                    onClick = { onFileShowChange(!isVisible) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = kmlLightBlue),
                )
                {
                    Text(text = "Close")
                }
            }
        }
    }
}