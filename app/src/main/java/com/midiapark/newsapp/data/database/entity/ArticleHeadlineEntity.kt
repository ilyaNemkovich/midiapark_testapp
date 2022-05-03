package com.midiapark.newsapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleHeadlineEntity(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "source_name") val source_name: String,
    @ColumnInfo(name = "source_url") val source_url: String
)
