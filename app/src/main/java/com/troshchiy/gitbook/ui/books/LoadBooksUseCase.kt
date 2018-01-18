package com.troshchiy.gitbook.ui.books

import com.tickengo.rider.base_mvp_interfaces.LoadDataView
import com.troshchiy.gitbook.App.Companion.component
import com.troshchiy.gitbook.domain.UseCase
import com.troshchiy.gitbook.extensions.transformer
import com.troshchiy.gitbook.network.DisposableLoadDataObserver
import com.troshchiy.gitbook.network.models.Book
import com.troshchiy.gitbook.network.models.Books
import io.reactivex.disposables.Disposable

class LoadBooksUseCase constructor(val onSuccess: (List<Book>) -> (Unit), val view: LoadDataView) : UseCase() {

    fun execute() = execute(null)

    override fun execute(request: Any?): Disposable {

        return component
                .gitBookService()
                .allBooks()
                .compose { transformer(it) }
                .subscribeWith(object : DisposableLoadDataObserver<Books>(view) {
                    override fun onSuccess(books: Books) {
                        onSuccess(books.list)
                    }
                })
    }
}