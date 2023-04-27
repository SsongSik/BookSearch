package com.flow.booksearch.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var backPressedCallback: OnBackPressedCallback? = null
    private var _binding: T? = null
    private val binding: T
        get() = _binding ?: throw IllegalStateException("Binding is not available")

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        backPressedCallback?.let {
            requireActivity().onBackPressedDispatcher.addCallback(this, it)
        }
    }

    override fun onDetach() {
        super.onDetach()
        backPressedCallback?.remove()
    }

    fun settingBackPressedCallback(callback: OnBackPressedCallback) {
        this.backPressedCallback = callback
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected abstract fun initView()

    protected abstract fun initListener()
}