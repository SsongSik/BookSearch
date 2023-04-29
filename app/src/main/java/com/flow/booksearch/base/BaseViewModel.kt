package com.flow.booksearch.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.booksearch.util.ErrorConstant
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel: ViewModel() {

    private val _exceptionStateFlow = MutableStateFlow<Int?>(null)
    val exceptionStateFlow: StateFlow<Int?> get() = _exceptionStateFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(throwable.message, throwable.toString())
        when (throwable) {
            is SocketException -> _exceptionStateFlow.value = ErrorConstant.EVENT_SOCKET_EXCEPTION
            is HttpException -> _exceptionStateFlow.value = ErrorConstant.EVENT_HTTP_EXCEPTION
            is UnknownHostException -> _exceptionStateFlow.value = ErrorConstant.EVENT_UNKNOWN_HOST_EXCEPTION
            else -> _exceptionStateFlow.value = ErrorConstant.EVENT_COROUTINE_EXCEPTION
        }
    }

    fun ViewModel.launch(dispatcher: CoroutineDispatcher = Dispatchers.IO, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatcher + exceptionHandler) {
            _isLoading.value = true
            try {
                block()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _exceptionStateFlow.value = null
    }
}