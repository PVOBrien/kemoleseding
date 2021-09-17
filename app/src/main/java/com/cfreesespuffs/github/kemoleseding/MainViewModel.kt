package com.cfreesespuffs.github.kemoleseding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<Screens>(Screens.TopScreens.Home)
    val currentScreen: LiveData<Screens> = _currentScreen

    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }
}