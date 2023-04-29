package com.flow.booksearch.ui.adapter.search

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.flow.booksearch.R
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.databinding.ItemSearchResultBinding

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val clickCallback: OnBookMarkViewHolderClick,
) : RecyclerView.ViewHolder(binding.root) {

    private var isBookmarked = false

    fun bind(book: Book, isBookmarkedInit: Boolean = false) {
        isBookmarked = isBookmarkedInit

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

            searchResultStarIv.setColorFilter(
                ContextCompat.getColor(
                    itemView.context,
                    if (isBookmarked) R.color.yellow_FFFF00 else android.R.color.black
                )
            )

            searchResultStarIv.setOnClickListener {
                isBookmarked = !isBookmarked
                searchResultStarIv.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        if (isBookmarked) R.color.yellow_FFFF00 else android.R.color.black
                    )
                )

                if (isBookmarked) {
                    clickCallback.clickBookMark(book)
                } else {
                    Log.d("gg1234", "삭제")
                    clickCallback.clickDeleteBookMark(book)
                }
            }
        }
    }
}
