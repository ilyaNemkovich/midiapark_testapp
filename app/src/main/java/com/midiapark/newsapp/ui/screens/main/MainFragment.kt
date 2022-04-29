package com.midiapark.newsapp.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.midiapark.newsapp.R
import com.midiapark.newsapp.databinding.MainFragmentBinding
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.screens.empty.DumbTabFragment
import com.midiapark.newsapp.ui.screens.news.NewsTabFragment
import com.midiapark.newsapp.ui.screens.search.SearchTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : ViewBindingFragment<MainFragmentBinding>(MainFragmentBinding::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = NewsTabFragment.newInstance()
        val search = SearchTabFragment.newInstance()
        val dumb = DumbTabFragment.newInstance()

        binding.bottomNavigationView.selectedItemId = R.id.news
        setCurrentFragment(news)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(dumb)
                R.id.search -> setCurrentFragment(search)
                R.id.more -> setCurrentFragment(dumb)
                R.id.profile -> setCurrentFragment(dumb)
                R.id.news -> setCurrentFragment(news)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_host, fragment)
            commit()
        }

    companion object {
        fun newInstance() = MainFragment()
    }
}