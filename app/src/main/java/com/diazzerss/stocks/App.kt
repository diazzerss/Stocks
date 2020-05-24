package com.diazzerss.stocks

import android.app.Application

/**
 * Created by Diaz on 05.05.2020.
 */
class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}