package com.flow.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>()
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
        with(binding) {
            searchTopBookmarkTv.setOnClickListener {
                val action = SearchFragmentDirections.actionFragmentSearchToFragmentBookMark()
                findNavController().navigate(action)
            }

            searchTopRecentlyTv.setOnClickListener {
                val action = SearchFragmentDirections.actionFragmentSearchToFragmentRecentSearch()
                findNavController().navigate(action)
            }
        }
    }

}