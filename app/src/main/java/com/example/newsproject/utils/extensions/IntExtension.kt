package com.example.newsproject.utils.extensions

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.newsproject.app.NewsApp

@ColorInt
fun Int.asColor(): Int {
    return try {
        ContextCompat.getColor(NewsApp.applicationContext, this)
    } catch (exception: Resources.NotFoundException) {
        exception.printStackTrace()
        this
    }
}