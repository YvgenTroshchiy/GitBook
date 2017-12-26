package com.troshchiy.gitbook.network

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// I am not sure about this class, because it behavior not obvious but anyway...
object DisposableManager {

    private val disposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.dispose()
    }
}