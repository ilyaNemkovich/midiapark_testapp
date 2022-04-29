package com.midiapark.newsapp.data.newtwork.dto

data class NewsSearchResponse(
    val totalArticles: Int,
    val articles: List<NewsResponse>
)

data class NewsResponse(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val source: SourceResponse
)

data class SourceResponse(
    val name: String,
    val url: String
)