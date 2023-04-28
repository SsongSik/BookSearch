package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentSearchBinding
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        searchViewModel.getSearchBook("android", 10, 1, "sim")
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