package com.flow.booksearch.ui.adapter.search

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.databinding.ItemSearchResultBinding

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val clickCallback: OnBookMarkViewHolderClick,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book : Book) {
        val title = "제목: ${book.title}"
        val author = "저자: ${book.author}"
        val publisher = "출판사: ${book.publisher}"
        val discount = "가격: ${book.discount}"

        with(binding) {
            searchResultTv.text = title
            searchResultWriterTv.text = author
            searchResultCompanyTv.text = publisher
            searchResultPriceTv.text = discount
            searchResultIv.load(book.image)

            searchResultStarIv.setOnClickListener {
                clickCallback.clickBookMark(book)
            }
        }
    }
}
