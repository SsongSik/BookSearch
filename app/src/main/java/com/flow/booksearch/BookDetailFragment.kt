package com.flow.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentBookDetailBinding

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookDetailBinding {
        return FragmentBookDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initListener() {

    }
}