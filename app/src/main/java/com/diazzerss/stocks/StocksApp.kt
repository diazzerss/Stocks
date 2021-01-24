package com.diazzerss.stocks

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StocksApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: StocksApp? = null
            private set
    }
}