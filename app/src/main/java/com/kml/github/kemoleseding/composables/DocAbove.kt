package com.kml.github.kemoleseding.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.kml.github.kemoleseding.*
import com.kml.github.kemoleseding.R
import com.kml.github.kemoleseding.objModules.DocDetails
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import com.kml.github.kemoleseding.ui.theme.kmlYellow
import java.io.File

@ExperimentalAnimationApi
@Composable
fun DocAbove(
    isVisible: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    passedDocDetails: List<DocDetails>,
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val downloadPercent = remember { mutableStateOf(.00) }
    val isDownLoading = remember { mutableStateOf(false) }
    val videoName = remember { mutableStateOf("") }
    val alertDialogItem = remember { mutableStateOf(passedDocDetails[0])}

    AlertDialog(context, openDialog, videoName.value, downloadPercent, isDownLoading)
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
                                        onClickLabel = "This is a File",
                                        onClick = {
                                            println("CLICK PIC ${item.docName}")
                                            val file =
                                                File("${context.getExternalFilesDir("Movies")}" + "/${item.docName}")
                                            println("Does $file exist: ${file.exists()}")
                                            if (!file.exists() && item.docDescription == "Video") {
                                                alertDialogItem.value = item
                                                videoName.value = item.docName
                                                openDialog.value = !file.exists()
                                            }
                                            if (item.picType == R.drawable.ic_baseline_play_circle_filled_24 && file.exists()) {
                                                openDialog.value = !file.exists()
                                                val uri = fileCreateAndUriVideo(
                                                    context,
                                                    item.docName
                                                )
                                                println("going to play video...")
                                                val intent = Intent(context, Video::class.java)
                                                intent.putExtra("video", uri.toString())
                                                context.startActivity(intent)
                                            } else if (item.docDescription != "Video") {
                                                println("Opening PDF")
                                                val uri: Uri = fileCreateAndUri(
                                                    context,
                                                    item.docName
                                                )
                                                openFile(
                                                    context,
                                                    uri
                                                )
                                            }
                                        }
                                    )
                            )
                            Text(
                                text = item.docDescription,
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                                    .clickable(
                                        enabled = false,
                                        onClickLabel = "Show the file",
                                        onClick = {
                                            println("ClICK TEXT ${item.docName}")
                                            if (item.picType == R.drawable.ic_baseline_play_circle_filled_24) {
                                                println("play video ${item.docName}")
                                            } else {
                                                val uri = fileCreateAndUri(context, item.docName)
                                                openFile(context, uri)
                                            }
                                        }
                                    ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                AnimatedVisibility(
                    visible = isDownLoading.value,
                    enter = slideInVertically({ -40 }),
                    exit = slideOutVertically({ -40 })
                ) {
                    DownloadBar(downloadPercent.value)
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

@Composable
fun DownloadBar(percent: Double) {
    Row(modifier = Modifier.padding(4.dp).fillMaxWidth(.5F)) {
        Text(text = "Download Status")
        LinearProgressIndicator(
            progress = percent.toFloat(),
            modifier = Modifier
                .padding(4.dp, 6.dp, 4.dp, 4.dp)
                .heightIn(8.dp),
            color = kmlLightBlue
        )
    }
}

@Composable
fun AlertDialog(
    context: Context,
    openD: MutableState<Boolean>,
    video: String,
    downloadPercent: MutableState<Double>,
    isDownload: MutableState<Boolean>
) {
    if (openD.value) {
        AlertDialog(
            onDismissRequest = { openD.value = false },
            title = { Text(text = "Download the Video?") },
            text = { Text(text = "Agreeing to this could use mobile data.") },
            confirmButton = {
                TextButton(onClick = {
                    openD.value = false
//                    val uri = fileCreateAndUriVideo(context, video)
                    isDownload.value = true
                    val aFile = File("${context.getExternalFilesDir("Movies")}/$video")
                    val options = StorageDownloadFileOptions.defaultInstance()
                    Amplify.Storage.downloadFile(video, aFile, options,
                        {
                            Log.i("S3.download", "% Download: ${it.fractionCompleted}")
                            downloadPercent.value = it.fractionCompleted
                        },
                        {
                            Log.i("S3.download", "Successfully downloaded: ${it.file.name}")
                            isDownload.value = false
                        },
                        {
                            Log.e("S3.download", "Download Failure", it)
                            isDownload.value = false
                        }
                    )

                }) {
                    Text(text = "Download")
                }
            },
            dismissButton = {
                TextButton(onClick = { openD.value = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}
