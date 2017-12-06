package com.troshchiy.gitbook.models

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable

class BooksViewModel : BaseObservable() {

    private val compositeDisposable = CompositeDisposable()

    fun reloadData() {

    }
}