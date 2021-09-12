package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cfreesespuffs.github.kemoleseding.MainViewModel
import com.cfreesespuffs.github.kemoleseding.Screens

@Composable
fun About(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.TopScreens.About)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "About", style = MaterialTheme.typography.h4)
    }
}