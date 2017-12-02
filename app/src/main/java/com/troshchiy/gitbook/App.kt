package com.troshchiy.gitbook

import com.troshchiy.gitbook.di.DaggerAppComponent
import com.troshchiy.gitbook.extensions.setStrictMode
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    companion object {
        lateinit var APP: App
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    init {
        APP = this
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()
    }
}