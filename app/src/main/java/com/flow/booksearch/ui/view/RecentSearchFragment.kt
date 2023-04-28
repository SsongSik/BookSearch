package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.databinding.FragmentRecentSearchBinding
import com.flow.booksearch.ui.adapter.keyword.RecentKeywordAdapter
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding>(){

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var recentKeywordAdapter: RecentKeywordAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentSearchBinding {
        return FragmentRecentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        setUpRecyclerView()
    }

    override fun initListener() {
        recentKeywordObserve()
    }

    private fun recentKeywordObserve() {
        lifecycleScope.launch {
            searchViewModel.recentKeyword.collectLatest {
                recentKeywordAdapter.submitList(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        recentKeywordAdapter = RecentKeywordAdapter()
        binding.searchRecentRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recentKeywordAdapter

            val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        recentKeywordAdapter.setOnItemClickListener {
            val action  = RecentSearchFragmentDirections.actionFragmentRecentSearchToFragmentSearch(it)
            findNavController().navigate(action)
        }
    }
}