package com.mlk.taskmanager

import android.app.Application
import com.mlk.taskmanager.util.PlacesUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialiser l'API Google Places
        PlacesUtil.initialize(this)
    }
} 