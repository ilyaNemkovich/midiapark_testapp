package com.midiapark.newsapp.data.repository

import com.midiapark.newsapp.data.newtwork.NewsService
import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    override suspend fun getSampleData(): NewsSearchResponse {
        return newsService.getSampleData()
    }
}