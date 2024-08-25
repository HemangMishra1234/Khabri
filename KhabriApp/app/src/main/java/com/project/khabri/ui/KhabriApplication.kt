package com.project.khabri.ui

import android.app.Application
import com.project.khabri.domain.di.pattagobhiModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KhabriApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KhabriApplication)
            modules(pattagobhiModules)
        }
    }
}