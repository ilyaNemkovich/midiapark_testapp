package com.midiapark.newsapp.data.newtwork.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsSearchResponse(
    val totalArticles: Int,
    val articles: List<NewsResponse>
)

@Parcelize
data class NewsResponse(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val source: SourceResponse
) : Parcelable

@Parcelize
data class SourceResponse(
    val name: String,
    val url: String
) : Parcelable