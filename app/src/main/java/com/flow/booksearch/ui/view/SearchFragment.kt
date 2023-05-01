package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flow.booksearch.R
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword
import com.flow.booksearch.databinding.FragmentSearchBinding
import com.flow.booksearch.ui.adapter.search.BookMarkAddDeleteClick
import com.flow.booksearch.ui.adapter.search.SearchResultAdapter
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import com.flow.booksearch.util.observeError
import com.flow.booksearch.util.observeLoading
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), BookMarkAddDeleteClick {

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var searchResultAdapter : SearchResultAdapter
    private val args : SearchFragmentArgs by navArgs<SearchFragmentArgs>()

    private var backKeyPressTime = 0L

    private var backStackStatus : Boolean = false
    private lateinit var searchKeyword : String

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
        if(backStackStatus) {
            searchViewModel.getSearchBook(searchKeyword)
        }
        else if(args.keyword != " ") {
            searchViewModel.getSearchBook(args.keyword)
            binding.searchEt.setText(args.keyword)
        }
    }

    override fun initListener() {
        observeLoading(searchViewModel, binding.progressBar)
        observeError(searchViewModel)

        settingBackPressedCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() > backKeyPressTime + 2000) {
                    backKeyPressTime = System.currentTimeMillis()
                    Snackbar.make(
                        binding.searchFragment, getString(R.string.back_button_warning), Snackbar.LENGTH_SHORT
                    ).show()

                } else if (System.currentTimeMillis() <= backKeyPressTime + 2000) {
                    activity?.finish()
                }
            }
        })

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
                searchKeyword = binding.searchEt.text.trim().toString()
                if(searchKeyword.isEmpty()) {
                    Toast.makeText(requireContext(), getString(R.string.search_keyword_not_text), Toast.LENGTH_SHORT).show()
                } else {
                    searchViewModel.insertKeyword(
                        recentKeyword =
                        RecentKeyword(
                            keyword = searchKeyword
                        )
                    )
                    backStackStatus = true
                    searchViewModel.getSearchBook(searchKeyword)
                }
            }
        }

        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            if(it.isEmpty() && !searchViewModel.isFirstPage) {
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

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    if (lastVisibleItemPosition == searchResultAdapter.itemCount - 1) {
                        val searchKeyword = binding.searchEt.text.trim().toString()
                        searchViewModel.getSearchBook(searchKeyword)
                    }
                }
            })
        }

        searchResultAdapter.setOnItemClickListener {
            val action  = SearchFragmentDirections.actionFragmentSearchToFragmentBookDetail(it)
            findNavController().navigate(action)
        }
    }

    override fun clickBookMark(book: Book) {
        Toast.makeText(requireContext(), getString(R.string.bookmark_text), Toast.LENGTH_SHORT).show()
        searchViewModel.insertBookMark(book.copy(timestamp = System.currentTimeMillis()))
    }

    override fun clickDeleteBookMark(book: Book) {
        Toast.makeText(requireContext(), getString(R.string.bookmark_delete_text), Toast.LENGTH_SHORT).show()
        searchViewModel.deleteBookMark(book)
    }
}