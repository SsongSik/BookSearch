package com.flow.booksearch.data.repository

import androidx.lifecycle.LiveData
import com.flow.booksearch.data.api.BookSearchApi
import com.flow.booksearch.data.db.BookSearchDatabase
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookSearchRepositoryImpl  @Inject constructor(
    private val db : BookSearchDatabase,
    private val api : BookSearchApi
) : BookSearchRepository{
    override suspend fun searchBooks(
        query: String,
        display: Int,
        start: Int,
        sort: String
    ): Response<SearchResponse> {
        return api.searchBooks(query, display, start, sort)
    }

    override suspend fun insertKeyword(recentKeyword: RecentKeyword) {
        db.keywordDao().insertKeyword(recentKeyword)
    }

    override fun getRecentKeyword(): Flow<List<RecentKeyword>> {
        return db.keywordDao().getRecentKeyword()
    }
}