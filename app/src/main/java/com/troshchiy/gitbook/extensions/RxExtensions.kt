package com.troshchiy.gitbook.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> transformer(it: Observable<T>) = it
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())