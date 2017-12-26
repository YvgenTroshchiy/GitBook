package com.troshchiy.gitbook.ui.books

import android.os.Bundle
import com.troshchiy.gitbook.R
import com.troshchiy.gitbook.network.models.Book
import com.troshchiy.gitbook.ui.LoadDataActivity
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : LoadDataActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadBooksUseCase(::handleBooks, this).execute()
    }

    private fun handleBooks(books: List<Book>) {
        debug { "Books size: ${books.size}" }
    }

    override fun showLoadingProgress() {
        info { "showLoadingProgress" }
    }

    override fun hideLoadingProgress() {
        info { "hideLoadingProgress" }
    }

    override fun showError(message: String) {
        error { "error: $message" }
        toast(message)
    }

    override fun hideError() {}
}