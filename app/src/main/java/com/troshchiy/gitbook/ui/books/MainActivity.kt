package com.troshchiy.gitbook.ui.books

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.troshchiy.gitbook.R
import com.troshchiy.gitbook.network.models.Book
import com.troshchiy.gitbook.ui.LoadDataActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : LoadDataActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        LoadBooksUseCase(::handleBooks, this).execute()
    }

    private fun setupRecyclerView() {
        val adapter = BooksAdapter {}

        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }
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