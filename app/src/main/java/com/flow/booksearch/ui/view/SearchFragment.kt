package com.flow.booksearch.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.databinding.FragmentSearchBinding
import com.flow.booksearch.ui.adapter.OnBookMarkViewHolderClick
import com.flow.booksearch.ui.adapter.SearchResultAdapter
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), OnBookMarkViewHolderClick {

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var searchResultAdapter : SearchResultAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        searchResultAdapter = SearchResultAdapter(this)
        binding.searchResultRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }

        searchResultAdapter.setOnItemClickListener {
            val action  = SearchFragmentDirections.actionFragmentSearchToFragmentBookDetail(it)
            findNavController().navigate(action)
        }
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

            searchResultBt.setOnClickListener {
                val searchKeyword = binding.searchEt.text.toString()
                if(searchKeyword.isEmpty()) {
                    Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    searchViewModel.getSearchBook(searchKeyword)
                }
            }
        }

        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            if(it.isEmpty()) {
                Toast.makeText(requireContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                searchResultAdapter.submitList(it)
            }
        }
    }

    override fun clickBookMark(book: Book) {
        //Room 저장
    }

}