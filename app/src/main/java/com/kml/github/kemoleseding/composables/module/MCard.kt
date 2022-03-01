package com.kml.github.kemoleseding.composables.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import com.kml.github.kemoleseding.ui.theme.kmlRed

@ExperimentalAnimationApi
@Composable
fun MCard(
    mCString: String,
    summary: String,
    picInt: Int,
    cardCount: Int,
    fileShow: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {
    println("the cardCount: $cardCount")
    var expanded by remember { mutableStateOf(false) }
    Button(
        onClick = {
            if (fileShow) {
                onFileShowChange(!fileShow)
            } else {
                expanded = !expanded
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = kmlLightBlue),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.shadow(
            5.dp,
            RoundedCornerShape(16.dp)
        )
    ) {
        Column(modifier = Modifier.padding(bottom = 3.dp)) {
            Text(
                text = mCString,
                Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp)
                    .offset(y = (-4).dp),
                fontFamily = SansSerif,
                fontSize = if (LocalConfiguration.current.screenWidthDp <= 400) 16.sp else 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            ModuleCardBody(
                summary,
                picInt,
                expanded,
                cardCount,
                onFileShowChange,
                onWhichModChange
            )
        }
    }
}