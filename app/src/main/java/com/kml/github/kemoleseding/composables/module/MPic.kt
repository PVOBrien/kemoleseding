package com.kml.github.kemoleseding.composables.module

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kml.github.kemoleseding.R

@ExperimentalAnimationApi
@Composable
fun MPic(
    picInt: Int,
    isExpanded: Boolean,
    ofCard: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                            val quickBool = true
                            onFileShowChange(!quickBool)
                            println("CLICK PIC")
                            onWhichModChange(ofCard)
                        })
            )
        }
    }
}