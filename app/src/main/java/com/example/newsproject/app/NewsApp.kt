package com.example.newsproject.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Companion.applicationContext = applicationContext
    }

    companion object {
        lateinit var applicationContext: Context
    }
}