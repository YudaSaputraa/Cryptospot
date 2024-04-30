package com.kom.cryptospot

import android.app.Application
import com.kom.cryptospot.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(AppModules.modules)
        }
    }
}
