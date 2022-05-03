package com.midiapark.newsapp.domain

import com.midiapark.newsapp.entities.ArticleHeadline
import kotlinx.coroutines.flow.Flow

interface NewsInteractor {
    suspend fun getTopHeadlines(): Flow<List<ArticleHeadline>>
}