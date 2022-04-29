package com.midiapark.newsapp.ui.screens.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.databinding.FragmentTabNewsBinding
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.screens.news.recycler.HeaderItem
import com.midiapark.newsapp.ui.screens.news.recycler.HeadlineItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsTabFragment : ViewBindingFragment<FragmentTabNewsBinding>(FragmentTabNewsBinding::class) {

    private val viewModel: NewsTabViewModel by viewModels()
    private val newsAdapter = GenericItemAdapter()
    private val headerAdapter = GenericItemAdapter()
    private val mainAdapter =
        FastAdapter.with(
            listOf(
                headerAdapter,
                newsAdapter
            )
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewSetup()
        subToLiveData()
    }

    private fun viewSetup() {
        binding.rvNews.adapter = mainAdapter
        binding.rvNews.setHasFixedSize(true)
        headerAdapter.set(listOf(HeaderItem("News")))
    }

    private fun subToLiveData() {
        viewModel.sampleData.observe(viewLifecycleOwner) {
            FastAdapterDiffUtil[newsAdapter] =
                it.articles.map { headline -> HeadlineItem(headline) }
        }
    }

    companion object {
        fun newInstance() = NewsTabFragment()
    }
}