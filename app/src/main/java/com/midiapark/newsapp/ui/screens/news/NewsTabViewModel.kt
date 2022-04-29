package com.midiapark.newsapp.ui.screens.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import com.midiapark.newsapp.domain.NewsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsTabViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _sampleData = MutableLiveData<NewsSearchResponse>()
    val sampleData: LiveData<NewsSearchResponse> = _sampleData

    init {
        viewModelScope.launch {
            _sampleData.value = newsInteractor.getSampleData()
        }
    }
}