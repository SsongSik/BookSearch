package com.flow.booksearch.util

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.flow.booksearch.R
import com.flow.booksearch.base.BaseViewModel
import kotlinx.coroutines.launch

fun Fragment.observeError(viewModel: BaseViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewModel.exceptionStateFlow.collect { errorCode ->
            errorCode?.let {
                showErrorDialog(errorCode)
                viewModel.clearError()
            }
        }
    }
}

fun Fragment.observeLoading(viewModel: BaseViewModel, progressBar: ProgressBar) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewModel.isLoading.collect { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}

fun Fragment.showErrorDialog(errorCode: Int) {
    val errorMessage = when (errorCode) {
        ErrorConstant.EVENT_SOCKET_EXCEPTION -> getString(R.string.error_socket_exception_text)
        ErrorConstant.EVENT_HTTP_EXCEPTION -> getString(R.string.error_http_exception_text)
        ErrorConstant.EVENT_UNKNOWN_HOST_EXCEPTION -> getString(R.string.error_unknown_host_exception_text)
        else -> getString(R.string.error_coroutine_exception_text)
    }

    AlertDialog.Builder(requireContext())
        .setTitle(R.string.dialog_error_text)
        .setMessage(errorMessage)
        .setPositiveButton(R.string.close_text) { dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .show()
}