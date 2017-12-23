package com.troshchiy.gitbook.domain

import io.reactivex.disposables.Disposable

abstract class UseCase {
    abstract fun execute(request: Any?): Disposable
}