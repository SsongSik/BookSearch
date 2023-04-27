package com.flow.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentSearchBinding

class FragmentSearch : BaseFragment<FragmentSearchBinding>()
{
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initListener() {

    }

}