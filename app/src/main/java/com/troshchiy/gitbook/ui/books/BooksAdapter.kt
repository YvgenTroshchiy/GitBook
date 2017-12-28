package com.troshchiy.gitbook.ui.books

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.troshchiy.gitbook.databinding.ListItemBookBinding
import com.troshchiy.gitbook.network.models.Book
import java.util.*

class BooksAdapter(private val itemClick: (Book) -> Unit) : RecyclerView.Adapter<BooksAdapter.BindingHolder>() {

    val books = LinkedList<Book>()

    var data: List<Book> = LinkedList()
        set(value) {
            books.clear()
            books.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.inflate<ListItemBookBinding>(inflater, R.layout.list_item_book, parent, false)
        val binding: ListItemBookBinding = ListItemBookBinding.inflate(inflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder?, position: Int) {
        holder?.bind(books[position])
    }

    inner class BindingHolder(private val binding: ListItemBookBinding?) : RecyclerView.ViewHolder(binding?.root) {
        fun bind(book: Book) {
            binding?.book = book
//            binding?.setVariable(BR.book, book)
            binding?.executePendingBindings()
        }
    }
}