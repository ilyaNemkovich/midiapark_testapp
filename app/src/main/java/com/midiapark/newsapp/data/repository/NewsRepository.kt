package com.midiapark.newsapp.data.repository

import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.SearchSetup
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(): Flow<List<ArticleHeadline>>
    suspend fun makeSearch(setup: SearchSetup, searchText: String): SearchResult
    suspend fun getSearchHistory(): Flow<List<String>>
}