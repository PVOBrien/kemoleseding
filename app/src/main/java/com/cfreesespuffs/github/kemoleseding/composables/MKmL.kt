package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cfreesespuffs.github.kemoleseding.MainViewModel
import com.cfreesespuffs.github.kemoleseding.objModules.Module
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue

@ExperimentalAnimationApi
@Composable
fun KemoLesedingTheme(
    modlist: List<Module>,
    fileShow: Boolean,
    onFileShowChange: (Boolean) -> Unit,
    viewModel: MainViewModel,
    onWhichModChange: (Int) -> Unit
) {
    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClick = {
                    println("onSurface")
                    onFileShowChange(!fileShow)
                }
            )
    ) {
        Text(
            text = "Click each for additional details",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            // https://foso.github.io/Jetpack-Compose-Playground/foundation/lazycolumn/
            modifier = Modifier
                .padding(top = 24.dp, start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            // todo: think about adding a top and bottom fade
        ) {
            itemsIndexed(modlist) { itemCount, item -> // https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#lazycolumn
                MCard(
                    item.title,
                    item.summary,
                    item.modPhoto,
                    itemCount, // itemCount is the index position of *the* item.
                    fileShow,
                    onFileShowChange,
                    onWhichModChange
                )
            }
        }
    }
}