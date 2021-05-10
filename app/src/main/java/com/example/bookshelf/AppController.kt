package com.example.bookshelf

import android.app.Application
import timber.log.Timber

class AppController: Application() {

    override fun onCreate() {
        super.onCreate()

        initTimberTree()
    }

    private fun initTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}