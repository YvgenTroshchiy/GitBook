package com.troshchiy.gitbook

import com.troshchiy.gitbook.di.AppComponent
import com.troshchiy.gitbook.di.DaggerAppComponent
import com.troshchiy.gitbook.extensions.getLogTag
import com.troshchiy.gitbook.extensions.logW
import com.troshchiy.gitbook.extensions.setStrictMode
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {
    private val tag = getLogTag<App>()

    companion object {
        lateinit var component: AppComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerAppComponent.builder().application(this).build()
        return component
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()
    }

    @Inject fun logInjection() {
        logW(tag, "")
    }
}