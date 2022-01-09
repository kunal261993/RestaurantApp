package com.example.restaurantapp.application

import android.app.Application
import android.content.Context

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        private var appContext: Context? = null
        fun getAppContext(): Context? {
            return appContext
        }
    }
}