package com.midiapark.newsapp.data.repository

import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse

interface NewsRepository {

    suspend fun getSampleData(): NewsSearchResponse
}