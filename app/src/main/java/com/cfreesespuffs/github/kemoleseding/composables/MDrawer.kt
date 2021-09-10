package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cfreesespuffs.github.kemoleseding.screensFromDrawer
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow

@Composable
fun MDrawerContent() { // onDestinationClicked: (route: String) -> Unit
    Column(
        modifier = Modifier
            .background(kmlYellow)
            .fillMaxWidth()
            .fillMaxHeight(),
    )
    {
        Spacer(modifier = Modifier.padding(3.dp))
        screensFromDrawer.forEach {
            Text(
                it.title,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                        println("clicked ${it.title}")
                    },
                fontSize = 6.em
            )
            Divider(
                modifier = Modifier // https://stackoverflow.com/questions/58898299/draw-a-line-in-jetpack-compose
                    .padding(5.dp)
                    .background(Color.Black),
                color = Color.Black,
                thickness = 3.dp
            )
        }
    }
}