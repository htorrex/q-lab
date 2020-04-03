package com.inqubits.mobile.qlab.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            // Android context
            androidContext(this@MovieApplication)
            // modules
            modules(mainKoinModule)
        }
    }
}
