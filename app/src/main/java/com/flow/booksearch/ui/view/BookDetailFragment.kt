package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    private val args : BookDetailFragmentArgs by navArgs<BookDetailFragmentArgs>()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookDetailBinding {
        return FragmentBookDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initBookWebSetting()
    }

    override fun initListener() {

    }

    private fun initBookWebSetting() {
        val book = args.book
        val decodedUrl = URLDecoder.decode(book.link, StandardCharsets.UTF_8.name())

        binding.bookDetailWv.apply {
            webViewClient = WebViewClient()
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
            loadUrl(decodedUrl)
        }
    }
}