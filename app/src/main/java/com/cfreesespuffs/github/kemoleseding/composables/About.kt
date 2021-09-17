package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cfreesespuffs.github.kemoleseding.MainViewModel
import com.cfreesespuffs.github.kemoleseding.Screens
import com.cfreesespuffs.github.kemoleseding.objModules.CoCardEntry
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue
import com.cfreesespuffs.github.kemoleseding.objModules.*

@Composable
fun About(viewModel: MainViewModel) {

    val coCardList: List<CoCardEntry> = listOf(tbc, pc)

    viewModel.setCurrentScreen(Screens.TopScreens.About)
    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            itemsIndexed(coCardList) { idx, item ->
                CoCard(
                    item.coPic,
                    item.coName,
                    item.coDesc
                )
                if (idx < coCardList.size - 1) // https://stackoverflow.com/questions/67139925/how-to-add-dividers-between-items-in-a-lazycolumn-jetpack-compose
                    Divider(
                        thickness = 6.dp,
                        color = Color.Black
                    ) else {
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}