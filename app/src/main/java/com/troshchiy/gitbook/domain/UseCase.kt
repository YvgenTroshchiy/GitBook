package com.troshchiy.gitbook.domain

import com.troshchiy.gitbook.App.Companion.component
import com.troshchiy.gitbook.network.GitBookService
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class UseCase<in Q, R>(protected var callback: Callback<R>) {

    var gitBookService: GitBookService = component.gitBookService()

    private var disposable: Disposable? = null
    fun unsubscribe() = disposable?.dispose()

    interface Callback<in R> {
        fun onSuccess(response: R)
        fun onError(throwable: Throwable)
    }

    abstract fun execute(request: Q): Disposable
}

abstract class SimpleCallback<in R>(private val errorAction: Consumer<Throwable>) : UseCase.Callback<R> {
    override fun onError(throwable: Throwable) = errorAction.accept(throwable)
}