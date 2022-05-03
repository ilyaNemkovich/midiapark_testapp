package com.midiapark.newsapp.data.repository

import com.midiapark.newsapp.data.database.entity.ArticleHeadlineEntity
import com.midiapark.newsapp.data.newtwork.dto.NewsResponse
import com.midiapark.newsapp.data.newtwork.dto.NewsSearchResponse
import com.midiapark.newsapp.data.newtwork.dto.SourceResponse
import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.Source

fun NewsSearchResponse.toDomain(): SearchResult {
    return SearchResult(
        totalArticles,
        articles.map {
            it.toDomain()
        }
    )
}

fun ArticleHeadlineEntity.toDomain(): ArticleHeadline {
    return ArticleHeadline(
        title, description, content, url, image, publishedAt, Source(source_name, source_url)
    )
}

fun ArticleHeadline.toDatabaseEntity(): ArticleHeadlineEntity {
    return ArticleHeadlineEntity(
        url, title, description, content, image, publishedAt, source.name, source.url
    )
}

fun NewsResponse.toDomain(): ArticleHeadline {
    return ArticleHeadline(
        title, description, content, url, image, publishedAt, source.toDomain()
    )
}

fun SourceResponse.toDomain(): Source {
    return Source(name, url)
}