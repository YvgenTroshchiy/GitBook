package com.troshchiy.gitbook.ui.books

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.troshchiy.gitbook.databinding.ListItemBookBinding
import com.troshchiy.gitbook.extensions.inflater
import com.troshchiy.gitbook.network.models.Book
import java.util.*

class BooksAdapter(private val itemClick: (Book) -> Unit) : RecyclerView.Adapter<BooksAdapter.BindingHolder>() {

    var books: List<Book> = LinkedList()
        set(value) {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }

    private val data = LinkedList<Book>()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            BindingHolder(ListItemBookBinding.inflate(parent.context.inflater(), parent, false))

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class BindingHolder(private val binding: ListItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }
}