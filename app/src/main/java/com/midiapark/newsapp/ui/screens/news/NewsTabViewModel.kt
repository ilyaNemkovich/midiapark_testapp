package com.midiapark.newsapp.ui.screens.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midiapark.newsapp.domain.NewsInteractor
import com.midiapark.newsapp.entities.ArticleHeadline
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsTabViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _topHeadlines = MutableLiveData<List<ArticleHeadline>>()
    val topHeadlines: LiveData<List<ArticleHeadline>> = _topHeadlines

    init {
        viewModelScope.launch {
            newsInteractor.getTopHeadlines().collect {
                _topHeadlines.value = it
            }
        }
    }
}