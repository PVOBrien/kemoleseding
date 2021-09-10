package com.cfreesespuffs.github.kemoleseding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.unit.dp
import com.cfreesespuffs.github.kemoleseding.objModules.docDetails
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow

@ExperimentalAnimationApi
@Composable
fun DocAbove(
    isVisible: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    passedDocDetails: List<docDetails>,
) {
    val context = LocalContext.current
    println("this is the current docDetail file path: ${passedDocDetails[0].docName}")
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
                                            openFile(context, item.docName)
                                        })
                            )
                            Text(
                                text = item.docDescription,
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