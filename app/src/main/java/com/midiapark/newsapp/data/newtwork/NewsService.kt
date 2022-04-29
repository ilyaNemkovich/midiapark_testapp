package com.midiapark.newsapp.data.newtwork

import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import retrofit2.http.GET

interface NewsService {

    @GET("search?q=example")
    suspend fun getSampleData(): NewsSearchResponse
}