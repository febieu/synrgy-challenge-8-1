package com.example.ch8

import android.app.Application
import com.example.ch8.di.koin.appModules
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        analytics = FirebaseAnalytics.getInstance(this)

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModules)
        }
    }
}
