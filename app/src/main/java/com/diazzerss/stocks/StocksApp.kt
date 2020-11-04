package com.diazzerss.stocks

import android.app.Application
import com.diazzerss.stocks.di.dataModule
import com.diazzerss.stocks.di.domainModule
import com.diazzerss.stocks.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StocksApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@StocksApp)
            modules(domainModule, presentationModule, dataModule)
        }
    }

    companion object {
        var instance: StocksApp? = null
            private set
    }
}