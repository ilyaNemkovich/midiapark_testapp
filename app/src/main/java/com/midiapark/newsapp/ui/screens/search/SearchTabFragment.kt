package com.midiapark.newsapp.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.databinding.FragmentTabSearchBinding
import com.midiapark.newsapp.databinding.ItemHeadlineBinding
import com.midiapark.newsapp.databinding.ItemSearchHistoryItemBinding
import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.entities.SearchSetup
import com.midiapark.newsapp.ui.article.ArticleActivity
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.extensions.requireListener
import com.midiapark.newsapp.ui.screens.news.recycler.HeaderItem
import com.midiapark.newsapp.ui.screens.news.recycler.HeadlineItem
import com.midiapark.newsapp.ui.screens.search.recycler.SearchHistoryItem
import com.midiapark.newsapp.ui.util.DebounceTextWatcher
import com.midiapark.newsapp.ui.util.NavigationManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.binding.listeners.addClickListener
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTabFragment :
    ViewBindingFragment<FragmentTabSearchBinding>(FragmentTabSearchBinding::class) {

    private val viewModel: SearchTabViewModel by viewModels()
    private val searchResultAdapter = GenericItemAdapter()
    private val headerAdapter = GenericItemAdapter()
    private val mainAdapter =
        FastAdapter.with(
            listOf(
                headerAdapter,
                searchResultAdapter
            )
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNews.adapter = mainAdapter
        setListeners()
        subToLiveData()
    }

    private fun subToLiveData() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            headerAdapter.set(listOf(HeaderItem("${it.totalArticles} news")))
            FastAdapterDiffUtil[searchResultAdapter] =
                it.articles.map { headline -> HeadlineItem(headline) }
        }
        viewModel.searchHistory.observe(viewLifecycleOwner) {
            headerAdapter.set(listOf(HeaderItem("Search History")))
            FastAdapterDiffUtil[searchResultAdapter] =
                it.map { item -> SearchHistoryItem(item) }
        }
    }

    private fun setListeners() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "search_setup_filter",
            viewLifecycleOwner
        ) { key, bundle ->
            Log.d("ssss", "key: $key | resultFromSearchTab $bundle")
            bundle.getParcelable<SearchSetup>("search_setup")
                ?.apply { viewModel.setSearchSetup(this) }
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "search_sort_by",
            viewLifecycleOwner
        ) { key, bundle ->
            Log.d("ssss", "key: $key | resultFromSearchTab $bundle")
            bundle.getParcelable<SearchSetup>("search_setup")
                ?.apply { viewModel.setSearchSetup(this) }
        }

        binding.ivFilter.setOnClickListener { openFilterFragment() }
        binding.ivOrder.setOnClickListener { openSortByBottomSheet() }
        binding.evSearch.addTextChangedListener(DebounceTextWatcher(threshold = 0) {
            viewModel.setSearchText(it)
        })
        binding.evSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.evSearch.text.toString().apply {
                    viewModel.setSearchText(this)
                }
                true
            }
            false
        }
        mainAdapter.apply {
            addClickListener({ b: ItemHeadlineBinding -> b.root }) { _, _, _, item ->
                item as HeadlineItem
                openArticle(item.model)
            }
        }
        mainAdapter.apply {
            addClickListener({ b: ItemSearchHistoryItemBinding -> b.root }) { _, _, _, item ->
                item as SearchHistoryItem
                binding.evSearch.setText(item.model)
            }
        }
    }

    private fun openFilterFragment() {
        requireListener<NavigationManager>().openFragment(SearchFilterFragment.newInstance(viewModel.searchSetup))
    }

    private fun openSortByBottomSheet() {
        SortByBottomSheetFragment.newInstance(viewModel.searchSetup)
            .show(childFragmentManager, "sortBy")
    }

    private fun openArticle(article: ArticleHeadline) {
        startActivity(ArticleActivity.newIntent(requireActivity(), article))
    }

    companion object {
        fun newInstance() = SearchTabFragment()
    }
}