package com.midiapark.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.midiapark.newsapp.data.database.entity.ArticleHeadlineEntity
import com.midiapark.newsapp.data.database.entity.SearchHistoryItemEntity

@Database(
    entities = [
        ArticleHeadlineEntity::class,
        SearchHistoryItemEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}