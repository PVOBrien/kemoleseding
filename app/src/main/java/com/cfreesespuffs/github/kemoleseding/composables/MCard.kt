package com.cfreesespuffs.github.kemoleseding.composables

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlRed

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
) { // https://joebirch.co/android/exploring-jetpack-compose-card/
    println("the cardCount: $cardCount")
    var expanded by remember { mutableStateOf(false) }
    Button(
        // to be used to show/animate https://developer.android.com/jetpack/compose/animation
        onClick = {
            if (fileShow) {
                onFileShowChange(!fileShow)
            } else {
                expanded = !expanded
            }
        },
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
                onFileShowChange,
                onWhichModChange
            )
        }
    }
}