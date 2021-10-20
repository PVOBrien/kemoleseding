package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cfreesespuffs.github.kemoleseding.MainViewModel
import com.cfreesespuffs.github.kemoleseding.screensFromDrawer
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlYellow

@Composable
fun MDrawerContent(viewModel: MainViewModel, onDestinationClicked: (route: String) -> Unit) {

    Column(
        modifier = Modifier
            .background(kmlYellow)
            .fillMaxWidth()
            .fillMaxHeight(),
    )
    {
        Spacer(modifier = Modifier.padding(3.dp))
        screensFromDrawer.forEach { screen ->
            Text(
                screen.title,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                        onDestinationClicked(screen.route)
                    },
                fontSize = 6.em
            )
            Divider(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.Black),
                color = Color.Black,
                thickness = 3.dp
            )
        }
//        Row() { // TODO: turn on once we have Setswana.
//
//            val mChecked = remember { mutableStateOf(viewModel.isLanguage) }
//            var language = "English"
//            if (mChecked.value) {
//                language = "Setswana"
//            }
//
//            Switch(
//                checked = mChecked.value,
//                modifier = Modifier.padding(top = 4.dp, start = 8.dp),
//                onCheckedChange = { _ ->
//                    mChecked.value = !mChecked.value
//                    println("vModel language is checked: ${viewModel.isLanguage}")
//                    viewModel.changeLang()
//                },
//                enabled = true,
//            )
//
//            Text(
//                text = language,
//                modifier = Modifier
//                    .padding(start = 12.dp),
//                fontSize = 6.em
//            )
//        }

    }
}