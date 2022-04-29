package com.midiapark.newsapp.ui.screens.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.databinding.FragmentTabSearchBinding
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTabFragment :
    ViewBindingFragment<FragmentTabSearchBinding>(FragmentTabSearchBinding::class) {

    private val viewModel: SearchTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = SearchTabFragment()
    }
}