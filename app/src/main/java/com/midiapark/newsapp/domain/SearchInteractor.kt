package com.midiapark.newsapp.domain

import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.SearchSetup
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun makeSearch(setup: SearchSetup, searchText: String): SearchResult
    suspend fun getSearchHistory(): Flow<List<String>>
}