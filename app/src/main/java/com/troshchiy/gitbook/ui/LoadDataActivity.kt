package com.troshchiy.gitbook.ui

import android.support.annotation.CallSuper
import com.tickengo.rider.base_mvp_interfaces.LoadDataView
import com.troshchiy.gitbook.network.DisposableManager
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.AnkoLogger

abstract class LoadDataActivity : DaggerAppCompatActivity(), LoadDataView, AnkoLogger {

    @CallSuper override fun onStop() {
        super.onStop()
        DisposableManager.dispose()
    }
}