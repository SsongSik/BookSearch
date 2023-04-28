package com.flow.booksearch.ui.adapter.search

import com.flow.booksearch.data.model.Book

interface OnBookMarkViewHolderClick {
    fun clickBookMark(book : Book)
}