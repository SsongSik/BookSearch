package com.flow.booksearch.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.booksearch.R
import com.flow.booksearch.base.BaseFragment
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.databinding.FragmentBookmarkBinding
import com.flow.booksearch.ui.adapter.keyword.RecentKeywordAdapter
import com.flow.booksearch.ui.adapter.search.OnBookMarkViewHolderClick
import com.flow.booksearch.ui.adapter.search.SearchResultAdapter
import com.flow.booksearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>(), OnBookMarkViewHolderClick {

    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var searchResultAdapter : SearchResultAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        setUpRecyclerView()
    }

    override fun initListener() {
        getBookMark()
    }

    private fun getBookMark() {
        lifecycleScope.launch {
            searchViewModel.bookMark.collectLatest {
                searchResultAdapter.submitList(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        searchResultAdapter = SearchResultAdapter(this, isBookmarkedInitially = true)
        binding.bookmarkResultRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }
        searchResultAdapter.setOnItemClickListener {
            val action  = BookMarkFragmentDirections.actionFragmentBookMarkToFragmentBookDetail(it)
            findNavController().navigate(action)
        }
    }

    override fun clickBookMark(book: Book) {

    }

    override fun clickDeleteBookMark(book: Book) {
        Toast.makeText(requireContext(), getString(R.string.bookmark_delete_text), Toast.LENGTH_SHORT).show()
        searchViewModel.deleteBookMark(book)
    }
}