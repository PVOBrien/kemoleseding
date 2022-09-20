package com.kml.github.kemoleseding

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel // https://codingwithmitch.com/courses/jetpack-compose-mvvm-for-beginners/hilt-viewmodels-and-dependency-injection/
class HiltViewModelThis @Inject constructor( // https://dagger.dev/hilt/view-model
    val handle: SavedStateHandle,
    val sPToVM: SharedPrefsToVM
) : ViewModel()

@Singleton
class SharedPrefsToVM @Inject constructor(@ApplicationContext context: Context) {
    val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
}

// https://stackoverflow.com/questions/67180046/how-to-inject-a-viewmodel-into-a-composable-function-using-hilt-jetpack-compose