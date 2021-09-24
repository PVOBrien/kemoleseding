package com.cfreesespuffs.github.kemoleseding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<Screens>(Screens.TopScreens.SplashScreen)
    val currentScreen: LiveData<Screens> = _currentScreen
    var isLanguage = false

    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }

    fun changeLang() {
        println("change, setLanguage()!")
        isLanguage = !isLanguage
    }
}