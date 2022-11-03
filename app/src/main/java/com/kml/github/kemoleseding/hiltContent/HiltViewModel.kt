package com.kml.github.kemoleseding.hiltContent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.kml.github.kemoleseding.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel // https://codingwithmitch.com/courses/jetpack-compose-mvvm-for-beginners/hilt-viewmodels-and-dependency-injection/
class HiltViewModelThis @Inject constructor( // https://dagger.dev/hilt/view-model
//    val sPToVM: SharedPrefsToVM
) : ViewModel() {
    private val _currentScreen: MutableLiveData<Screens> = MutableLiveData<Screens>(Screens.TopScreens.SplashScreen)
    val currentScreen: LiveData<Screens> = _currentScreen
    var isSetswana: Boolean = false
    fun changeLanguage() {
        println("change, setLanguage()!")
        isSetswana = !isSetswana
    }

    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }
}

@Singleton
class SharedPrefsToVM @Inject constructor(@ApplicationContext context: Context) { // https://developer.android.com/training/dependency-injection/hilt-android#java
    val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
} // https://www.bing.com/search?PC=U523&q=provide+app+context+with+Android+Hilt&pglt=515&FORM=ANNTA1&ntref=1

// https://stackoverflow.com/questions/67180046/how-to-inject-a-viewmodel-into-a-composable-function-using-hilt-jetpack-compose