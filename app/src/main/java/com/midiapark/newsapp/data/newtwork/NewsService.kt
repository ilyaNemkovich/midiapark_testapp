package com.midiapark.newsapp.data.newtwork

import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("search?q=example")
    suspend fun getSampleData(): NewsSearchResponse

    @GET("top-headlines")
    suspend fun topHeadlines(): NewsSearchResponse

    @GET("search")
    suspend fun makeSearch(
        @Query("q") text: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
        @Query("sortby") order: String? = "publishedAt",
        @Query("in") searchIn: String?,
    ): NewsSearchResponse
}