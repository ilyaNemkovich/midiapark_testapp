package com.midiapark.newsapp.ui.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midiapark.newsapp.domain.SearchInteractor
import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.SearchSetup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchTabViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var searchText = ""
    var searchSetup: SearchSetup = SearchSetup.getDefault()
        private set

    private val _searchResult = MutableLiveData<SearchResult>()
    val searchResult: LiveData<SearchResult> = _searchResult

    private val _searchHistory = MutableLiveData<List<String>>()
    val searchHistory: LiveData<List<String>> = _searchHistory

    init {
        viewModelScope.launch {
            _searchHistory.value = searchInteractor.getSearchHistory().first()
        }
    }

    fun setSearchText(text: String) {
        searchText = text
        updateResult()
    }

    fun setSearchSetup(searchSetup: SearchSetup) {
        this.searchSetup = searchSetup
        updateResult()
    }

    private fun updateResult() {
        Log.d("ssss", "$searchSetup : $searchText")
        if (searchText.isNotEmpty()){
            viewModelScope.launch {
                _searchResult.value = searchInteractor.makeSearch(searchSetup, searchText)
            }
        } else {
            viewModelScope.launch {
                _searchHistory.value = searchInteractor.getSearchHistory().first()
            }
        }
    }
}