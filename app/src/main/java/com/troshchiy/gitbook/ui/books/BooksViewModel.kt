package com.troshchiy.gitbook.ui.books

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable

class BooksViewModel : BaseObservable() {

    private val compositeDisposable = CompositeDisposable()

    fun reloadData() {

    }
}