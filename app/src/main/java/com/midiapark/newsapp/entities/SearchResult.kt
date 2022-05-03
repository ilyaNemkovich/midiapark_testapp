package com.midiapark.newsapp.entities

data class SearchResult(
    val totalArticles: Int,
    val articles: List<ArticleHeadline>
)