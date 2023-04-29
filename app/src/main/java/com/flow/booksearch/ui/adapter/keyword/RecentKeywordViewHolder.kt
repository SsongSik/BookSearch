package com.flow.booksearch.ui.adapter.keyword

import androidx.recyclerview.widget.RecyclerView
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.databinding.ItemSearchRecentBinding

class RecentKeywordViewHolder(
    private val binding: ItemSearchRecentBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recentKeyword: RecentKeyword){
        with(binding) {
            searchRecentTv.text = recentKeyword.keyword
        }
    }
}
