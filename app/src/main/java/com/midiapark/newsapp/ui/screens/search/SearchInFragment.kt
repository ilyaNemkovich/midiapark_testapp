package com.midiapark.newsapp.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.midiapark.newsapp.databinding.FragmentSearchInBinding
import com.midiapark.newsapp.entities.SearchIn
import com.midiapark.newsapp.entities.SearchSetup
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchInFragment :
    ViewBindingFragment<FragmentSearchInBinding>(FragmentSearchInBinding::class) {

    private val viewModel: SearchInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getParcelable<SearchSetup>("setup")?.apply {
            viewModel.setSearchSetup(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        subToLiveData()
    }

    private fun subToLiveData() {
        viewModel.searchSetup.observe(viewLifecycleOwner) { setup ->
            Log.d("ssss", "observe: $setup")
            setup.searchIn.forEach {
                when (it) {
                    SearchIn.TITLE -> binding.sTitle.isChecked = true
                    SearchIn.DESCRIPTION -> binding.sDescription.isChecked = true
                    SearchIn.CONTENT -> binding.sContent.isChecked = true
                }
            }
        }
    }

    private fun setListeners() {
        binding.sContent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel.addOption(SearchIn.CONTENT)
            else viewModel.removeOption(SearchIn.CONTENT)
        }
        binding.sDescription.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel.addOption(SearchIn.DESCRIPTION)
            else viewModel.removeOption(SearchIn.DESCRIPTION)
        }
        binding.sTitle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel.addOption(SearchIn.TITLE)
            else viewModel.removeOption(SearchIn.TITLE)
        }
        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.mbApply.setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                "search_setup_search_in", Bundle().apply {
                    putParcelable("search_setup", viewModel.getSearchSetup())
                }
            )
            binding.ivBack.performClick()
        }
    }

    companion object {
        fun newInstance(setup: SearchSetup) = SearchInFragment().apply {
            arguments = Bundle().apply {
                putParcelable("setup", setup)
            }
        }
    }
}