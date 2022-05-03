package com.midiapark.newsapp.ui.screens.search

import android.os.Bundle
import android.view.View
import com.midiapark.newsapp.R
import com.midiapark.newsapp.databinding.FragmentSortByBinding
import com.midiapark.newsapp.entities.ResultOrder
import com.midiapark.newsapp.entities.SearchSetup
import com.midiapark.newsapp.ui.base.ViewBindingBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SortByBottomSheetFragment :
    ViewBindingBottomSheetFragment<FragmentSortByBinding>(FragmentSortByBinding::class) {

    val searchSetup: SearchSetup by lazy { requireNotNull(requireArguments().getParcelable("setup")) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setListeners()
    }

    private fun setupView() {
        searchSetup.apply {
            when (this.order) {
                ResultOrder.RELEVANCE -> binding.rgGroup.check(R.id.rb_relevance)
                ResultOrder.UPLOAD_DATE -> binding.rgGroup.check(R.id.rb_date)
            }
        }
    }

    private fun setListeners() {
        binding.rgGroup.setOnCheckedChangeListener { _, checkedId ->
            val newOrder = when (checkedId) {
                R.id.rb_date -> ResultOrder.UPLOAD_DATE
                R.id.rb_relevance -> ResultOrder.RELEVANCE
                else -> error("Unsupported id")
            }
            val resultSetup = searchSetup.copy(order = newOrder)
            requireActivity().supportFragmentManager.setFragmentResult(
                "search_setup_filter", Bundle().apply {
                    putParcelable("search_setup", resultSetup)
                }
            )
            dismiss()
        }
    }

    companion object {
        fun newInstance(setup: SearchSetup) = SortByBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putParcelable("setup", setup)
            }
        }
    }
}