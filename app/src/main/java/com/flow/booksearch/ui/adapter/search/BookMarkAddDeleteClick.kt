package com.flow.booksearch.ui.adapter.search

import com.flow.booksearch.data.model.Book

interface BookMarkAddDeleteClick {
    fun clickBookMark(book : Book)

    fun clickDeleteBookMark(book : Book)
}