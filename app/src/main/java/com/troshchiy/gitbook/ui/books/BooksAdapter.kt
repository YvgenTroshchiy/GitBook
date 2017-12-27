package com.troshchiy.gitbook.ui.books

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.troshchiy.gitbook.BR
import com.troshchiy.gitbook.network.models.Book
import java.util.*

class BooksAdapter(private val itemClick: (Book) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val books: List<Book> = Collections.emptyList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        holder.getBinding().setVariable(BR.item, books[position])
//        holder.getBinding().executePendingBindings()
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}