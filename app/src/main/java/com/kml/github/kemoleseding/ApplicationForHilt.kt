package com.kml.github.kemoleseding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationForHilt : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}