package com.flow.booksearch.ui.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.databinding.ItemSearchResultBinding

class SearchResultAdapter(
    listener: OnBookMarkViewHolderClick,
    private val isBookmarkedInitially: Boolean = false
) : ListAdapter<Book, SearchResultViewHolder>(
    BookDiffCallback
) {
    private val clickCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickCallback
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val book = currentList[position]
        holder.bind(book, isBookmarkedInitially)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(book) }
        }
    }

    private var onItemClickListener: ((Book) -> Unit)? = null

    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}