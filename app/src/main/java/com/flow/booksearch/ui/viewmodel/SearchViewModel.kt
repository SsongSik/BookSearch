package com.flow.booksearch.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flow.booksearch.base.BaseViewModel
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.data.repository.BookSearchRepository
import com.flow.booksearch.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository,
) : BaseViewModel() {

    //api
    private val _searchResult = MutableLiveData<List<Book>>()
    val searchResult : LiveData<List<Book>>
        get() = _searchResult

    private var currentPage = 1
    private val itemsPerPage = 10

    private var isLastPage = false
    private var isFetching = false

    private var currentQuery = ""

    val isFirstPage: Boolean
        get() = currentPage == 1

    fun getSearchBook(query: String) {
        if (query != currentQuery) {
            currentQuery = query
            currentPage = 1
            isLastPage = false
            _searchResult.value = emptyList()
        }

        if (!isFetching && !isLastPage) {
            fetchData(query)
        }
    }

    private fun fetchData(query : String) = launch {
        isFetching = true
        val response = bookSearchRepository.searchBooks(query, itemsPerPage, currentPage, Constant.SIM)

        if (response.isSuccessful) {
            response.body()?.let { result ->
                val currentBooks = _searchResult.value ?: emptyList()
                val newBooks = result.items.filter { newItem ->
                    currentBooks.none { it.isbn == newItem.isbn }
                }
                val updatedBooks = currentBooks + newBooks
                _searchResult.postValue(updatedBooks)

                isLastPage = updatedBooks.size >= result.total
                currentPage++
            }
        }
        isFetching = false
    }

    //db
    fun insertKeyword(recentKeyword: RecentKeyword) = launch {
        bookSearchRepository.insertKeyword(recentKeyword)
    }

    val recentKeyword : Flow<List<RecentKeyword>> = bookSearchRepository.getRecentKeyword()

    fun insertBookMark(book : Book) = launch {
        bookSearchRepository.insertBookMark(book)
    }

    val bookMark : Flow<List<Book>> = bookSearchRepository.getBookMarks()

    fun deleteBookMark(book : Book) = launch {
        bookSearchRepository.deleteBookMark(book)
    }
}