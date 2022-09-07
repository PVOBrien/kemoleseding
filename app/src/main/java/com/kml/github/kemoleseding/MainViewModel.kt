package com.kml.github.kemoleseding

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.prefs.Preferences

class MainViewModel : ViewModel() {

//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings") // Todo: https://developer.android.com/codelabs/android-preferences-datastore#0

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