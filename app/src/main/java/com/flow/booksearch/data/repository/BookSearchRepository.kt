package com.flow.booksearch.data.repository

import com.flow.booksearch.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {

    suspend fun searchBooks(
        query : String,
        display : Int,
        start : Int,
        sort : String,
    ) : Response<SearchResponse>
}