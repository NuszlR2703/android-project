package com.zoltanlorinczi.project_retrofit

import android.app.Application
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager

class App : Application() {

    companion object {
        lateinit var sharedPreferences: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }
}