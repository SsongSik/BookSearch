package com.flow.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentRecentSearchBinding

class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentSearchBinding {
        return FragmentRecentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initListener() {

    }
}