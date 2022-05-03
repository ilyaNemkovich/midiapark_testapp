package com.midiapark.newsapp.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.R
import com.midiapark.newsapp.databinding.MainFragmentBinding
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.screens.empty.DumbTabFragment
import com.midiapark.newsapp.ui.screens.news.NewsTabFragment
import com.midiapark.newsapp.ui.screens.search.SearchTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : ViewBindingFragment<MainFragmentBinding>(MainFragmentBinding::class)
     {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener {
            viewModel.setTab(menuIdToTag(it.itemId))
            true
        }
        viewModel.tab.observe(viewLifecycleOwner) { tag ->
            openTab(tag)
            binding.bottomNavigationView.menu.findItem(tagToMenuId(tag)).isChecked = true
        }
    }

    private fun openTab(tag: String) {
        childFragmentManager.commit {
            childFragmentManager.fragments.forEach { hide(it) }
            childFragmentManager.findFragmentByTag(tag).let {
                if (it != null) show(it)
                else add(binding.fcvHost.id, getNewInstanceOfFragmentByTag(tag), tag)
            }
        }
    }

    private fun getNewInstanceOfFragmentByTag(tag: String): Fragment {
        return when (tag) {
            "home" -> DumbTabFragment.newInstance()
            "search" -> SearchTabFragment.newInstance()
            "more" -> DumbTabFragment.newInstance()
            "profile" -> DumbTabFragment.newInstance()
            "news" -> NewsTabFragment.newInstance()
            else -> error("Wrong name")
        }
    }

    private fun tagToMenuId(tag: String): Int {
        return when (tag) {
            "home" -> R.id.home
            "search" -> R.id.search
            "more" -> R.id.more
            "profile" -> R.id.profile
            "news" -> R.id.news
            else -> error("Wrong name")
        }
    }

    private fun menuIdToTag(menuId: Int): String {
        return when (menuId) {
            R.id.home -> "home"
            R.id.search -> "search"
            R.id.more -> "more"
            R.id.profile -> "profile"
            R.id.news -> "news"
            else -> error("Wrong id")
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}