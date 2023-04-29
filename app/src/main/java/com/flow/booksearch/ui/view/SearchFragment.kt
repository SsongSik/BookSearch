package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.booksearch.R
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.databinding.FragmentSearchBinding
import com.flow.booksearch.ui.adapter.search.OnBookMarkViewHolderClick
import com.flow.booksearch.ui.adapter.search.SearchResultAdapter
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), OnBookMarkViewHolderClick {

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var searchResultAdapter : SearchResultAdapter
    private val args : SearchFragmentArgs by navArgs<SearchFragmentArgs>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initArgsSetting()
        setUpRecyclerView()
    }

    private fun initArgsSetting() {
        if(args.keyword != " ") {
            searchViewModel.getSearchBook(args.keyword)
            binding.searchEt.setText(args.keyword)
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
                    Toast.makeText(requireContext(), getString(R.string.search_keyword_not_text), Toast.LENGTH_SHORT).show()
                } else {
                    searchViewModel.insertKeyword(
                        recentKeyword =
                        RecentKeyword(
                            index = 0,
                            keyword = searchKeyword
                        )
                    )
                    searchViewModel.getSearchBook(searchKeyword)
                }
            }
        }

        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            if(it.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.search_result_not_text), Toast.LENGTH_SHORT).show()
            } else {
                searchResultAdapter.submitList(it)
            }
        }
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

    override fun clickBookMark(book: Book) {
        Toast.makeText(requireContext(), getString(R.string.bookmark_text), Toast.LENGTH_SHORT).show()
        searchViewModel.insertBookMark(book)
    }

    override fun clickDeleteBookMark(book: Book) {
        Toast.makeText(requireContext(), getString(R.string.bookmark_delete_text), Toast.LENGTH_SHORT).show()
        searchViewModel.deleteBookMark(book)
    }

}