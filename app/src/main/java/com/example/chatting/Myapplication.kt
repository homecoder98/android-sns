package com.example.chatting

import android.app.Application
import android.content.SharedPreferences

class Myapplication : Application() {
    companion object{
        lateinit var prefs : PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}