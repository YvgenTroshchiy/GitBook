package com.troshchiy.gitbook.ui.books

import android.os.Bundle
import com.tickengo.rider.base_mvp_interfaces.LoadDataView
import com.troshchiy.gitbook.R
import com.troshchiy.gitbook.extensions.getLogTag
import com.troshchiy.gitbook.extensions.logE
import com.troshchiy.gitbook.extensions.logW
import com.troshchiy.gitbook.network.models.Book
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.toast

class MainActivity : DaggerAppCompatActivity(), LoadDataView {
    private val tag = getLogTag<MainActivity>()

    private val disposables = CompositeDisposable()

    override fun showLoadingProgress() {}
    override fun hideLoadingProgress() {}
    override fun showError(message: String) {
        logE(tag, "error: $message")
        toast(message)
    }

    override fun hideError() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposables.add(BooksUseCase(::handleBooks, this).execute())
    }

    private fun handleBooks(books: List<Book>) {
        logW(tag, "Books size: ${books.size}")
    }

    override fun onStop() {
        super.onStop()
        disposables.dispose()
    }
}