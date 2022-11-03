package com.kml.github.kemoleseding.composables

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
import com.kml.github.kemoleseding.Screens
import com.kml.github.kemoleseding.hiltContent.HiltViewModelThis
import com.kml.github.kemoleseding.objModules.CoCardEntry
import com.kml.github.kemoleseding.objModules.*

@Composable
fun About(viewModel: HiltViewModelThis, isSETS: Boolean, ) {

    val coCardList: List<CoCardEntry> = listOf(tbc, pc)

    viewModel.setCurrentScreen(Screens.TopScreens.About)
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            itemsIndexed(coCardList) { idx, item ->
                CoCard(
                    item.coPic,
                    item.coName,
                    if (!isSETS) item.coDescENG else item.coDescSETS
                )
                if (idx < coCardList.size - 1)
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