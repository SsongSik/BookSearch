package com.flow.booksearch.ui.adapter.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.databinding.ItemSearchRecentBinding
import com.flow.booksearch.databinding.ItemSearchResultBinding
import com.flow.booksearch.ui.adapter.search.OnBookMarkViewHolderClick
import com.flow.booksearch.ui.adapter.search.SearchResultViewHolder

class RecentKeywordAdapter() : ListAdapter<RecentKeyword, RecentKeywordViewHolder>(
    BookDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentKeywordViewHolder {
        return RecentKeywordViewHolder(
            ItemSearchRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecentKeywordViewHolder, position: Int) {
        val keyword = currentList[position]
        holder.bind(keyword)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let{ it(keyword.keyword) }
        }
    }


    private var onItemClickListener : ((String) -> Unit)? = null

    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }


    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<RecentKeyword>() {
            override fun areItemsTheSame(oldItem: RecentKeyword, newItem: RecentKeyword): Boolean {
                return oldItem.index == newItem.index
            }

            override fun areContentsTheSame(oldItem: RecentKeyword, newItem: RecentKeyword): Boolean {
                return oldItem == newItem
            }
        }
    }
}