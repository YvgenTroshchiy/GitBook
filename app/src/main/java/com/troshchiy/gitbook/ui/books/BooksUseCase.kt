package com.troshchiy.gitbook.ui.books

import com.troshchiy.gitbook.domain.UseCase
import com.troshchiy.gitbook.extensions.transformer
import com.troshchiy.gitbook.models.Book
import io.reactivex.disposables.Disposable

class BooksUseCase constructor(callback: Callback<List<Book>>) : UseCase<Unit?, List<Book>>(callback) {

    override fun execute(request: Unit?): Disposable =
            gitBookService
                    .allBooks()
                    .compose { transformer(it) }
                    .subscribe({ callback.onSuccess(it.list) }, { callback.onError(it) })

}