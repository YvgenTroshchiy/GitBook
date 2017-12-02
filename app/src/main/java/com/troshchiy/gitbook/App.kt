package com.troshchiy.gitbook

import android.app.Application
import android.os.StrictMode

class App : Application() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()
    }

    private fun setStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
    }
}