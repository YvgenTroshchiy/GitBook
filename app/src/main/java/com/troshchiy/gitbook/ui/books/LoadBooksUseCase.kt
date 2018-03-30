package com.troshchiy.gitbook.ui.books

import com.tickengo.rider.base_mvp_interfaces.LoadDataView
import com.troshchiy.gitbook.App.Companion.component
import com.troshchiy.gitbook.domain.UseCase
import com.troshchiy.gitbook.extensions.transformer
import com.troshchiy.gitbook.network.DisposableLoadDataObserver
import com.troshchiy.gitbook.network.models.Book
import com.troshchiy.gitbook.network.models.Books
import io.reactivex.disposables.Disposable

class LoadBooksUseCase constructor(val view: LoadDataView, val onSuccess: (List<Book>) -> (Unit)) : UseCase() {

    fun execute() = execute(null)

    override fun execute(request: Any?): Disposable {

        //TODO: component should be passed thought constructor
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