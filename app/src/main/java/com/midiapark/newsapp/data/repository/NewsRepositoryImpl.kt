package com.midiapark.newsapp.data.repository

import com.midiapark.newsapp.data.database.ArticleDao
import com.midiapark.newsapp.data.database.SearchHistoryDao
import com.midiapark.newsapp.data.database.entity.SearchHistoryItemEntity
import com.midiapark.newsapp.data.newtwork.NewsService
import com.midiapark.newsapp.entities.ArticleHeadline
import com.midiapark.newsapp.entities.SearchResult
import com.midiapark.newsapp.entities.SearchSetup
import com.midiapark.newsapp.entities.toApiString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val searchHistoryDao: SearchHistoryDao,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getTopHeadlines(): Flow<List<ArticleHeadline>> {
        return withContext(Dispatchers.IO) {
            launch {
                try {
                    val newArticles =
                        newsService.topHeadlines().articles.map { it.toDomain().toDatabaseEntity() }
                    articleDao.insertList(newArticles)
                } catch (e: Exception) {
                }
            }
            articleDao.getAll().map { list -> list.map { it.toDomain() } }
        }

    }

    override suspend fun makeSearch(setup: SearchSetup, searchText: String): SearchResult {
        return withContext(Dispatchers.IO) {
            launch {
                searchHistoryDao.insertItem(SearchHistoryItemEntity(searchText))
            }

            newsService.makeSearch(
                text = "\"$searchText\"",
                from = DateTimeFormatter.ISO_INSTANT.format(
                    setup.dateFrom.truncatedTo(ChronoUnit.SECONDS).toInstant(
                        ZoneOffset.UTC
                    )
                ),
                to = DateTimeFormatter.ISO_INSTANT.format(
                    setup.dateTo.truncatedTo(ChronoUnit.SECONDS).toInstant(
                        ZoneOffset.UTC
                    )
                ),
                searchIn = setup.searchIn.joinToString(",").lowercase(),
                order = setup.order.toApiString()
            ).toDomain()
        }
    }

    override suspend fun getSearchHistory(): Flow<List<String>> {
        return searchHistoryDao.getAll().map { it.map { item -> item.searchItem } }
    }
}