package com.flow.booksearch.ui.adapter.keyword

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.databinding.ItemSearchRecentBinding
import com.flow.booksearch.databinding.ItemSearchResultBinding
import com.flow.booksearch.ui.adapter.search.OnBookMarkViewHolderClick

class RecentKeywordViewHolder(
    private val binding: ItemSearchRecentBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recentKeyword: RecentKeyword){
        with(binding) {
            searchRecentTv.text = recentKeyword.keyword
        }
    }
}
