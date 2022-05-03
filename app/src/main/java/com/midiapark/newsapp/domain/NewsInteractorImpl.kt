package com.midiapark.newsapp.domain

import com.midiapark.newsapp.data.repository.NewsRepository
import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.entities.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override suspend fun getTopHeadlines(): Flow<List<ArticleHeadline>> {
        return newsRepository.getTopHeadlines()
    }
}