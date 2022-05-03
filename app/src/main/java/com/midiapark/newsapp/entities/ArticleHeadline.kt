package com.midiapark.newsapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleHeadline(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val source: Source
) : Parcelable

@Parcelize
data class Source(
    val name: String,
    val url: String
) : Parcelable