package com.troshchiy.gitbook.network

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// Am not sure about this class, because it behavior not obvious but anyway for a test...
object DisposableManager {

    private val disposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.dispose()
    }
}