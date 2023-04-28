package com.flow.booksearch.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val items: List<Book>,
    val lastBuildDate: String,
    val total : Int,
    val start : Int,
    val display : Int
)
