package com.troshchiy.gitbook.ui

import android.os.Bundle
import com.troshchiy.gitbook.R
import com.troshchiy.gitbook.extensions.getLogTag
import com.troshchiy.gitbook.extensions.logE
import com.troshchiy.gitbook.extensions.logW
import com.troshchiy.gitbook.extensions.transformer
import com.troshchiy.gitbook.network.GitBookService
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val TAG = getLogTag<MainActivity>()

    @Inject lateinit var gitBookService: GitBookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequest()
    }

    private fun sendRequest() {
        //TODO:
        val subscription = gitBookService
                .allBooks()
                .compose { transformer(it) }
                .subscribe(
                        {
                            logW(TAG, it.message())
                        },
                        {
                            logE(TAG, "allBooks", it)
                        })
    }
}