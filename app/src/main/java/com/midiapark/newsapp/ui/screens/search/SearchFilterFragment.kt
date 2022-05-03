package com.midiapark.newsapp.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.midiapark.newsapp.databinding.FragmentSearchFilterBinding
import com.midiapark.newsapp.entities.SearchIn
import com.midiapark.newsapp.entities.SearchSetup
import com.midiapark.newsapp.ui.util.NavigationManager
import com.midiapark.newsapp.ui.base.ViewBindingFragment
import com.midiapark.newsapp.ui.extensions.requireListener
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

@AndroidEntryPoint
class SearchFilterFragment :
    ViewBindingFragment<FragmentSearchFilterBinding>(FragmentSearchFilterBinding::class) {

    private val viewModel: SearchFilterViewModel by viewModels()

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
        viewModel.searchSetup.observe(viewLifecycleOwner) {
            binding.tvFromPicker.text = it.dateFrom.toLocalDate().toString()
            binding.tvToPicker.text = it.dateTo.toLocalDate().toString()
            binding.tvSearchInOptions.text = when (it.searchIn.size) {
                SearchIn.values().size -> "All"
                else -> it.searchIn.joinToString()
            }
        }
    }

    private fun setListeners() {
        binding.tvFromPicker.setOnClickListener {
            MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText("Select from date")
                .build()
                .apply {
                    show(this@SearchFilterFragment.childFragmentManager, "DATE_PICKER")
                    addOnPositiveButtonClickListener {
                        viewModel.updateFromDate(
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                        )
                    }
                }
        }
        binding.tvToPicker.setOnClickListener {
            MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText("Select to date")
                .build()
                .apply {
                    show(this@SearchFilterFragment.childFragmentManager, "DATE_PICKER")
                    addOnPositiveButtonClickListener {
                        viewModel.updateToDate(
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                        )
                    }
                }
        }
        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvSearchIn.setOnClickListener { openSearchInFragment() }
        binding.mbApply.setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                "search_setup_filter", Bundle().apply {
                    putParcelable("search_setup", viewModel.getSearchSetup())
                }
            )
            binding.ivBack.performClick()
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "search_setup_search_in",
            viewLifecycleOwner
        ) { _, bundle ->
            Log.d("ssss", "resultFromFilter $bundle")
            bundle.getParcelable<SearchSetup>("search_setup")
                ?.apply { viewModel.setSearchSetup(this) }
        }
    }

    private fun openSearchInFragment() {
        requireListener<NavigationManager>().openFragment(SearchInFragment.newInstance(viewModel.getSearchSetup()))
    }

    companion object {
        fun newInstance(setup: SearchSetup) = SearchFilterFragment().apply {
            arguments = Bundle().apply {
                putParcelable("setup", setup)
            }
        }
    }
}