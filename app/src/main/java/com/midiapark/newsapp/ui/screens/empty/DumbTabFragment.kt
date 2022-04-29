package com.midiapark.newsapp.ui.screens.empty

import android.os.Bundle
import android.view.View
import com.midiapark.newsapp.databinding.FragmentTabDumbBinding
import com.midiapark.newsapp.ui.base.ViewBindingFragment

class DumbTabFragment :
    ViewBindingFragment<FragmentTabDumbBinding>(FragmentTabDumbBinding::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = DumbTabFragment()
    }
}