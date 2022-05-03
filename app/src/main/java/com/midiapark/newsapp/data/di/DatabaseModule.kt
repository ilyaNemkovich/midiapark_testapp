package com.midiapark.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.midiapark.newsapp.data.database.AppDatabase
import com.midiapark.newsapp.data.database.ArticleDao
import com.midiapark.newsapp.data.database.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "database-app"
        ).build()
    }


    @Provides
    fun provideArticleDao(
        database: AppDatabase
    ): ArticleDao = database.articleDao()

    @Provides
    fun provideSearchHistoryDao(
        database: AppDatabase
    ): SearchHistoryDao = database.searchHistoryDao()
}