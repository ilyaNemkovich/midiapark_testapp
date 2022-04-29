package com.midiapark.newsapp.domain

import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse

interface NewsInteractor {
    suspend fun getSampleData(): NewsSearchResponse
}