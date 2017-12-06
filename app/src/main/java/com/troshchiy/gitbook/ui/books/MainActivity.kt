package com.troshchiy.gitbook.ui.books

import android.os.Bundle
import com.troshchiy.gitbook.R
import com.troshchiy.gitbook.domain.SimpleCallback
import com.troshchiy.gitbook.extensions.getLogTag
import com.troshchiy.gitbook.extensions.logE
import com.troshchiy.gitbook.extensions.logW
import com.troshchiy.gitbook.network.models.Book
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.functions.Consumer
import org.jetbrains.anko.toast

class MainActivity : DaggerAppCompatActivity() {

    private val tag = getLogTag<MainActivity>()

    private val onError = Consumer<Throwable> {
        logE(tag, "OnError $it")
        toast(it.message.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BooksUseCase(object : SimpleCallback<List<Book>>(onError) {
            override fun onSuccess(response: List<Book>) {
                logW(tag, "Books size: ${response.size}")
            }
        }).execute(Unit)
    }
}