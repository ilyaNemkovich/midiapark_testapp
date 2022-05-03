package com.midiapark.newsapp.domain

import com.midiapark.newsapp.data.repository.NewsRepository
import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.SearchSetup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : SearchInteractor {

    override suspend fun makeSearch(setup: SearchSetup, searchText: String): SearchResult {
        return newsRepository.makeSearch(setup, searchText)
    }

    override suspend fun getSearchHistory(): Flow<List<String>> {
        return newsRepository.getSearchHistory()
    }
}