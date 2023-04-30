package com.flow.booksearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flow.booksearch.base.BaseViewModel
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.data.repository.BookSearchRepository
import com.flow.booksearch.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository,
) : BaseViewModel() {

    //api
    private val _searchResult = MutableLiveData<List<Book>>()
    val searchResult : LiveData<List<Book>>
        get() = _searchResult

    fun getSearchBook(query : String) = launch {
        val response = bookSearchRepository.searchBooks(query, 10, 1, Constant.SIM)

        if (response.isSuccessful) {
            response.body()?.let{
                _searchResult.postValue(it.items)
            }
        }
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