package com.midiapark.newsapp.ui.screens.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.data.newtwork.dto.NewsResponse
import com.midiapark.newsapp.databinding.FragmentTabNewsBinding
import com.midiapark.newsapp.databinding.ItemHeadlineBinding
import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.ui.article.ArticleActivity
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.screens.news.recycler.HeaderItem
import com.midiapark.newsapp.ui.screens.news.recycler.HeadlineItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.binding.listeners.addClickListener
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
        setListeners()
    }

    private fun setListeners() {
        mainAdapter.apply {
            addClickListener({ b: ItemHeadlineBinding -> b.root }) { _, _, _, item ->
                item as HeadlineItem
                openArticle(item.model)
            }
        }
    }

    private fun viewSetup() {
        binding.rvNews.adapter = mainAdapter
        binding.rvNews.setHasFixedSize(true)
        headerAdapter.set(listOf(HeaderItem("News")))
    }

    private fun subToLiveData() {
        viewModel.topHeadlines.observe(viewLifecycleOwner) {
            FastAdapterDiffUtil[newsAdapter] =
                it.map { headline -> HeadlineItem(headline) }
        }
    }

    private fun openArticle(article: ArticleHeadline) {
        startActivity(ArticleActivity.newIntent(requireActivity(), article))
    }

    companion object {
        fun newInstance() = NewsTabFragment()
    }
}