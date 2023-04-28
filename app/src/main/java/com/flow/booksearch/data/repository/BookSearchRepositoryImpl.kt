package com.flow.booksearch.data.repository

import com.flow.booksearch.data.api.BookSearchApi
import com.flow.booksearch.data.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookSearchRepositoryImpl  @Inject constructor(
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
}