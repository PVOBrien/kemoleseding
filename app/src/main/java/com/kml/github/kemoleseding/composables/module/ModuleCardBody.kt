package com.kml.github.kemoleseding.composables.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalAnimationApi
@Composable
fun ModuleCardBody(
    mSummary: String,
    picInt: Int,
    isExpanded: Boolean,
    cardCount: Int,
    onFileShowChange: (Boolean) -> Unit,
    onWhichModChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
    ) {
        MPic(picInt, isExpanded, cardCount, onFileShowChange, onWhichModChange)
        ModuleDetails(mSummary, isExpanded)
    }
}