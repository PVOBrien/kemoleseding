package com.kml.github.kemoleseding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableLiveData<Screens>(Screens.TopScreens.SplashScreen)
    val currentScreen: LiveData<Screens> = _currentScreen
    var isSetswana = false

    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }

    fun changeLanguage() { // TODO: implement when we have Setswana
        println("change, setLanguage()!")
        isSetswana = !isSetswana
    }
}