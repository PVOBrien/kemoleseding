package com.kml.github.kemoleseding.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kml.github.kemoleseding.MainViewModel
import com.kml.github.kemoleseding.Screens
import com.kml.github.kemoleseding.composables.module.MCard
import com.kml.github.kemoleseding.objModules.*
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue

@ExperimentalAnimationApi
@Composable
fun KemoLesedingTheme(
    viewModel: MainViewModel,
    modDown: (Boolean) -> Unit,
    openMod: Boolean
) {
    viewModel.setCurrentScreen((Screens.TopScreens.Home))
    var whichMod by remember { mutableStateOf(0) }
    val modList: List<Module> = listOf(modOne, modTwo, modThree, modFour)

    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClick = {
                    if (openMod) modDown(!openMod)
                }
            )
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            // todo: think about adding a top and bottom fade
        ) {
            itemsIndexed(modList) { itemCount, item ->
                if (itemCount == 0) {
                    Text(
                        text = "Click each for additional details",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                MCard(
                    item.title,
                    item.summary,
                    item.modPhoto,
                    itemCount,
                    openMod,
                    onFileShowChange = modDown,
                    onWhichModChange = { whichMod = it }
                )
                if (itemCount == modList.size - 1) {
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
        DocAbove(
            openMod,
            onFileShowChange = modDown,
            modList[whichMod].docList
        )
    }
}