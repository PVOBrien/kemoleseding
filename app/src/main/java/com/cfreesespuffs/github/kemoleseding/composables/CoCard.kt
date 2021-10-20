package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue

@Composable
fun CoCard(
    cosPic: Int,
    cosName: String,
    cosDesc: String
) {
    Card() {
        Column(
            modifier = Modifier
                .background(SolidColor(kmlLightBlue)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cosName,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(8.dp)
            )
            Image(
                painter = painterResource(id = cosPic),
                contentDescription = "Thusang Bana Centre logo",
                modifier = Modifier
                    .shadow(12.dp, clip = true)
                    .background(Color.White)
                    .size(128.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = cosDesc,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .background(
                        SolidColor(Color.White),
                        alpha = .5F
                    )
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}