package com.flow.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentBookmarkBinding

class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initListener() {

    }
}