package com.midiapark.newsapp.domain

import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import com.midiapark.newsapp.data.repository.NewsRepository
import javax.inject.Inject


class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override suspend fun getSampleData(): NewsSearchResponse {
        return newsRepository.getSampleData()
    }
}