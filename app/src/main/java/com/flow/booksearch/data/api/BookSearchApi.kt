package com.flow.booksearch.data.api

import com.flow.booksearch.data.model.SearchResponse
import com.flow.booksearch.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookSearchApi {

    @Headers(
        "X-Naver-Client-Id: ${Constant.CLIENT_ID}",
        "X-Naver-Client-Secret: ${Constant.CLIENT_SECRET}"
    )
    @GET("v1/search/book.json")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("display") display : Int,
        @Query("start") start : Int,
        @Query("sort") sort : String,
    ) : Response<SearchResponse>

}