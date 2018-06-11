package com.troshchiy.gitbook.di.repository

import com.troshchiy.gitbook.extensions.logE
import com.troshchiy.gitbook.extensions.transformer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface LocalDataSource<D> {
    fun set(data: D)
    fun get(): Observable<D>
    fun clear()
}

interface NetDataSource<in Q, D> {
    /** If query is null load all data */
    fun load(query: Q?): Observable<D>
}

open class Repository<in Q, D>(
    private val localDataSource: LocalDataSource<D>,
    private val netDataSource: NetDataSource<Q, D>
) {

    val subject = PublishSubject.create<D>().toSerialized()

    private var cashedData: D? = null

    fun set(data: D) {
        cashedData = data
        localDataSource.set(data)
        post(data)
    }

    private fun post(data: D) {
        if (subject.hasObservers()) subject.onNext(data)
    }

    fun get(query: Q?): Single<D?> =
        if (cashedData != null) Single.create { cashedData }
        else Observable.concat(localDataSource.get(), netDataSource.load(query))
            .filter { it != null }
            .first(null)
            .doOnSuccess { it?.let { cashedData = it } }

    fun refresh() {
        cashedData = null
        localDataSource.clear()

        netDataSource.load(null)
            .compose { transformer(it) }
            .subscribe(
                {
                    cashedData = it
                    localDataSource.set(it)
                    post(it)
                },
                { logE("Repository", "refresh", it) })
    }

    fun clear() {
        localDataSource.clear()
        cashedData = null
//        post(null)
    }
}