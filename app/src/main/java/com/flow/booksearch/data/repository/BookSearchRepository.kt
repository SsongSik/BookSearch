package com.flow.booksearch.data.repository

import androidx.lifecycle.LiveData
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BookSearchRepository {

    //api
    suspend fun searchBooks(
        query : String,
        display : Int,
        start : Int,
        sort : String,
    ) : Response<SearchResponse>

    //db
    suspend fun insertKeyword(recentKeyword: RecentKeyword)

    fun getRecentKeyword() : Flow<List<RecentKeyword>>

    suspend fun insertBookMark(book : Book)

    fun getBookMarks() : Flow<List<Book>>

    suspend fun deleteBookMark(book : Book)
}